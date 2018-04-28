package com.k.kwidget.group.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;



public class MyViewGroup extends ViewGroup {

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 确定每个自组件的位置
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 测量容器的尺寸
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 绘制容器
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
