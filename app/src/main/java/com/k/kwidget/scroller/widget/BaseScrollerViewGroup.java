package com.k.kwidget.scroller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Scroller;


public class BaseScrollerViewGroup extends ViewGroup {

    private Scroller scroller;
    private Button button;

    public BaseScrollerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller=new Scroller(context);
        button=new Button(context);
        LayoutParams layoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        button.setText("android 自定义组件");
        button.setLayoutParams(layoutParams);
        addView(button);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        button.layout(10,10,button.getMeasuredWidth()+10,
                button.getMeasuredHeight()+10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
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

    /**
     * 开始滚动，外部调用
     */
    public void start(){
        //从当前位置开始滚动，x方向滚动900，y方向滚动0，滚动时常10s
        scroller.startScroll(getScrollX(),getScrollY(),-500,0,5000);
        //重新绘制
       postInvalidate();

    }

    /**
     * 取消滚动
     */
    public void abort(){
        scroller.abortAnimation();
    }

}
