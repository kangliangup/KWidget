package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class BallMoveView extends View {

    //小球水平位置
    private  int x;

    //小球固定垂直位置
    private static final int y=100;

    //小球半径
    private static final  int radius=30;

    //小球颜色
    public static final int ballColor= Color.RED;

    //移动方向，true表示像右移动，false向左移动
    private  boolean direction;

    private Paint paint;

    public BallMoveView(Context context) {
        super(context);


    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //初始化paint，参数表示去锯齿
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ballColor);
        x=radius;
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //根据x、y的坐标画一个小球
        canvas.drawCircle(x,y,radius,paint);

        //改变x的值，调用invalidate后
        //小球因x的值的改变，发生动画
        int width=this.getMeasuredWidth();

        if(x<radius){
            direction=true;
        }

        if(x>=width-radius){
            direction=false;
        }
        x=direction?x+5:x-5;
    }
}
