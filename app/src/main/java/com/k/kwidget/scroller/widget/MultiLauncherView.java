package com.k.kwidget.scroller.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.solver.widgets.Snapshot;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 触摸滑屏
 */
public class MultiLauncherView extends ViewGroup{

    private Scroller scroller;
    private static final String TAG = "MultiLauncherView";
    private int touchSlop=0;//最小滑动距离，超过了才被认为是滑动,默认为8

    public static final int TOUCH_STATE_STOP=0x001;//停止状态
    public static final int TOUCH_STATE_FLING=0x002;//滑动状态
    private int touchState=TOUCH_STATE_STOP;
    private float lastionMoionX=0;//上次触摸屏的x位置

    public MultiLauncherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller=new Scroller(context);
        touchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int n=getChildCount();
        int width=(r-l)/n;//分屏的宽度
        int height=b-t;//容器的高度

        for (int i = 0; i <n ; i++) {

            View child=getChildAt(i);
            int left=i*width;
            int right=(i+1)*width;
            child.layout(left,t,right, height);
        }



    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int width=measureWidth(widthMeasureSpec);
        int height=measureHeight(heightMeasureSpec);

        setMeasuredDimension(width,height);

    }

    /**
     *测量宽度
     */
    private int measureWidth(int widthMeasureSpec) {

        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);
        int width;

        if(mode==MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("Must not be MeasureSpec.AT_MOST.");
        }
        else {
            width=size;
        }

        return width*getChildCount();
    }

    /**
     *测量高度
     */
    private int measureHeight(int heightMeasureSpec) {

        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        int height;

        if(mode==MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("Must not be MeasureSpec.AT_MOST.");
        }
        else {
            height=size;
        }

        return height;
    }

    /**
     *滑动事件处理
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {



        int action=ev.getAction();
        final int x= (int) ev.getX();



        if(ev.getAction()==MotionEvent.ACTION_MOVE&&touchState==TOUCH_STATE_STOP){
            System.out.println("123");
            return true;
        }

        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                lastionMoionX=x;
                touchState=scroller.isFinished()?TOUCH_STATE_STOP:TOUCH_STATE_FLING;
                System.out.println("123");
                break;

            case MotionEvent.ACTION_MOVE:
                //滑动距离过小，不算滑动
                final  int dx= (int) Math.abs(x-lastionMoionX);
                if(dx>touchSlop){
                    touchState=TOUCH_STATE_FLING;
                }
                break;

            case MotionEvent.ACTION_UP:
                touchState=TOUCH_STATE_STOP;
                break;

            default:
                break;


        }

        //滑动时拦截
            return touchState != TOUCH_STATE_STOP;
    }

    private int curScreen;//当前屏
    private VelocityTracker velocityTracker;//速率跟踪器

    /**
     * 惯性滚动，
     */
    public void moveToScreen(int whichScreen){
        Log.i(TAG, "moveToScreen: ");

        curScreen=whichScreen;
        Log.i(TAG, "curScreen: "+curScreen);

        //如果当前屏幕大于等于最后一屏，则返回最后一屏
        if(curScreen>getChildCount()-1){
            curScreen=getChildCount()-1;
        }

        //如果小于1，则返回第一屏
        if(curScreen<1){
            curScreen=0;
        }

        //已滚动的距离
        int scrollX=getScrollX();
        //每一屏的宽度
        int splitWidth=getWidth()/getChildCount();
        //要移动的距离（剩下的惯性距离）
        int dx=curScreen*splitWidth-scrollX;
        //开始移动
        scroller.startScroll(scrollX,0,dx,0,Math.abs(dx));
        invalidate();
    }

    public void moveToDestination(){
        Log.i(TAG, "moveToDestination: ");
        //每一屏的宽度
        int splitWith=getMeasuredWidth()/getChildCount();
        //判断是否回滚还是进入下一分屏
        int toScreen=(getScrollX()+splitWith/2)/splitWith;
        //移动到目标分屏
        moveToScreen(toScreen);
    }

    public void moveToNext(){
        moveToScreen(curScreen+1);
    }

    public void moveToPre(){
        moveToScreen(curScreen-1);
    }


    public static final int SNAP_VELOCITY=600;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(velocityTracker==null){
            velocityTracker=VelocityTracker.obtain();
        }

        velocityTracker.addMovement(event);
        super.onTouchEvent(event);

        final int x= (int) event.getX();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                //手指按下时，如果正在滚动，则停止滚动
                if(scroller!=null&&!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                lastionMoionX=x;
                break;


            case MotionEvent.ACTION_MOVE:

                //当第一页时不允许向右滑动，
                if(curScreen==0&&x-lastionMoionX>0){
                    return false;
                }
                //最后一屏时不允许向左滑动
                if(curScreen==getChildCount()-1&&x-lastionMoionX<0){
                    return false;
                }

                //随手指滚动
                int dx= (int) (lastionMoionX-x);
                scrollBy(dx,0);
                lastionMoionX=x;
                break;

            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(1000);
                int velocityX= (int) velocityTracker.getXVelocity();

                //velocityX的正负值可以判断滑动方向
                if(velocityX> 0&&curScreen>0){
                    moveToPre();
                }

                else if(velocityX<0&&curScreen<getChildCount()-1){
                    moveToNext();
                }

                else{
                    moveToDestination();
                }

                if(velocityTracker != null) {
                    this.velocityTracker.clear();
                    this.velocityTracker.recycle(); 
                    this.velocityTracker = null;
                }

                touchState = TOUCH_STATE_STOP;

                break;

            case MotionEvent.ACTION_CANCEL:
                touchState=TOUCH_STATE_STOP;
                break;

            default:
                break;



        }
        return true;
    }


    @Override
    public void computeScroll() {

        if(scroller.computeScrollOffset()){
            //设置容器内  组件的新位置
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            //重绘以刷新产生动画
            postInvalidate();
        }
    }
}
