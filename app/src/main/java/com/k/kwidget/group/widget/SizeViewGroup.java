package com.k.kwidget.group.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SizeViewGroup extends ViewGroup {

    public SizeViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        //创建一个按钮
        TextView textView=new TextView(context);
        textView.setText("android");
        textView.setBackgroundColor(Color.YELLOW);

        //当前容器中添加自组件
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(200,200);
        addView(textView,layoutParams);
        //设置容器背景颜色
        setBackgroundColor(Color.TRANSPARENT);



    }


    /**
     *确定子组件的位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        View textView=  this.getChildAt(0);
        textView.layout(50,50,textView.getMeasuredWidth()+50,textView.getMeasuredHeight()+50);



    }


    @Override
    protected void onDraw(Canvas canvas) {

        //画一个红色边框
        RectF rectF=new RectF(20,20,getMeasuredWidth(),getMeasuredHeight());
        rectF.inset(2,2);
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        Path path=new Path();
        path.addRoundRect(rectF,20,20, Path.Direction.CCW);
        canvas.drawPath(path,paint);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量所有子组件的大小
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //测量自身
        setMeasuredDimension(500,500);
    }
}
