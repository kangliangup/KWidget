package com.k.kwidget.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 *  通常来说，自定义组件有三种定义方式
 *  1、继承自view、viewGroup
 *  2、继承现有组件ImageView等
 *  3、将多个已有的组件组合在一起
 */
public class FirstView extends View {

    //在java代码中使用
    public FirstView(Context context) {
        super(context);
    }

    //在xml布局中使用
    public FirstView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //需要自定义属性和style时使用
    public FirstView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public FirstView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
