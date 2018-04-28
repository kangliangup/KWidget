package com.k.kwidget.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.k.kwidget.R;
import com.k.kwidget.util.DensityUtils;


/**
 * 侧边栏
 */
public class SliderMenu extends ViewGroup {

    private static final String TAG = "SliderMenu";
    private boolean isOpen;
    private boolean canMoving;
    private boolean hasDivider;

    private int slidingWidth;
    private int touchWidth;
    private int dividerWidth;
    private int screenWidth;
    private Paint paint;
    private int preX,firstX;
    private Scroller scroller;




    public SliderMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.SliderMenu);
        dividerWidth=typedArray.getDimensionPixelOffset(R.styleable.SliderMenu_divider_width,0);
        touchWidth=typedArray.getDimensionPixelOffset(R.styleable.SliderMenu_touch_width,0);
        slidingWidth=typedArray.getDimensionPixelOffset(R.styleable.SliderMenu_sliding_width,0);

        if(dividerWidth>0){
           hasDivider=true;
        }

        screenWidth=getScreenWidth(context);
        setBackgroundColor(Color.alpha(255));
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(dividerWidth);
        scroller=new Scroller(context);


        typedArray.recycle();
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i <getChildCount() ; i++) {
            View child=getChildAt(i);
            if(i==0){
                child.layout(-slidingWidth,t,0, b);
            }
            else if(i==1){
                child.layout(0,t,screenWidth, b);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画分割线
        if(hasDivider){
            canvas.drawLine(slidingWidth,0,slidingWidth+dividerWidth,getMeasuredHeight(),paint);
        }
        Log.i(TAG, "onDraw: ");
    }


    /**
     * 获取屏幕宽度 * @param context * @return
     */
    private int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) getContext()
            .getSystemService(Context.WINDOW_SERVICE); Point point = new Point();
        assert wm != null;
        wm.getDefaultDisplay().getSize(point);
        return point.x;
    }

    /**
     *侧边栏只能有两个自容器，一个侧边栏，另一个作为主界面
     */
    @Override
    public void addView(View child, LayoutParams params) {
//        super.addView(child, params);
        if(getChildCount()>2){
            throw new ArrayIndexOutOfBoundsException("Children count can't be more than 2.");
        }
        super.addView(child, params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int width=measureWidth(widthMeasureSpec);
        int height=measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);

    }


    /**
     *总宽度=侧边栏宽度+分割线宽度+主界面宽度
     */
    private int measureWidth(int widthMeasureSpec) {

        int mode=MeasureSpec.getMode(widthMeasureSpec);

      if(mode==MeasureSpec.AT_MOST){
            throw new IllegalStateException("layout_width can not be wrap_content.");
        }

        return screenWidth+slidingWidth+dividerWidth;
    }

    private int measureHeight(int heightMeasureSpec) {

        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        int height=0;
        if(mode==MeasureSpec.EXACTLY){
            height=size;
        }
        else if(mode==MeasureSpec.AT_MOST){
            throw new IllegalStateException("layout_width can not be wrap_content");
        }
        return height;
    }




    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:

                if(isOpen){
                    canMoving =true;
                }else{
                    //侧边栏拖拽的触发范围 x为0～touchWidth
                    if(ev.getX()>touchWidth){
                        canMoving =false;
                    }else{
                        canMoving =true;
                    }
                }


                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                break;

            case MotionEvent.ACTION_CANCEL:
                canMoving =false;
                break;


        }
        Log.i(TAG, "canMoving: "+canMoving);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(!canMoving){
            return false;
        }

        int x= (int) event.getX();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                preX=x;
                firstX=x;
                break;

            case MotionEvent.ACTION_MOVE:
               int dx=x-preX;


               if(!isOpen&&dx<0){
                   break;
               }

               if(x-firstX>=slidingWidth){
                   break;
               }

                if(getScrollX()<=-slidingWidth){

                    System.out.println("--====="+ DensityUtils.dp2px(getContext(),100));
                    break;
                }
                scrollBy(-dx,0);
                System.out.println("scrollx"+getScrollX());
               preX=x;
                break;

            case MotionEvent.ACTION_UP:
                dx=x-firstX;
                int remain=slidingWidth-Math.abs(dx);
//
//                //dx右为正，左为负
//                //展开侧边栏
                if(dx>0&&!isOpen){
                    scroller.startScroll(getScrollX(),0,(-slidingWidth)-getScrollX(),0);
                    isOpen=true;
                }
//                //收起侧边栏
//                else if(dx<0&&isOpen){
//                    scroller.startScroll(getScrollX(),0,-remain,0);
//                }
//                else{
//                    //校正
//                    scroller.startScroll(getScrollX(),0,dx,0);
//                }
//                firstX=0;
//                invalidate();
                break;

            default:
                break;
        }

        return canMoving;
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            postInvalidate();
        }

    }


    /**
     * 打开侧边栏
     */
    public void open(){
        if(!isOpen){
            scroller.startScroll(0,0,-slidingWidth,0);
            invalidate();
           isOpen=true;
        }


    }

    /**
     * 关闭侧边栏
     */
    public void close(){
        if(isOpen){
            scroller.startScroll(-slidingWidth,0,slidingWidth,0);
            invalidate();
            isOpen=false;
        }
    }

    /**
     * 打开关闭侧边栏
     */
    public void toggle(){
        if(isOpen){
            close();
        }else{
            open();
        }
    }
}
