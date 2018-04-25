package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;




public class CoordinateView extends View {

    private Paint paint;
    private RectF rectRect;
    private RectF rectScale;


    public CoordinateView(Context context) {
        super(context);
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

         paint=new Paint(Paint.ANTI_ALIAS_FLAG);
         rectRect=new RectF(50,50,200,200);
         rectScale=new RectF(50,50,450,450);


        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        /*--------平移坐标绘制--------*/
        //保存现场
        canvas.save();
        for (int i = 0; i < 10; i++) {
            canvas.drawRect(rectRect,paint);
//            //水平移动坐标
            canvas.translate(15,15);
        }
        //恢复现场
        canvas.restore();


        /*--------缩放坐标绘制--------*/
        //平移坐标，让接下来的图形绘制在下一个图形的上面
        canvas.translate(0,400);
        //保存现场
        canvas.save();

        for (int i = 0; i < 10; i++) {
            canvas.drawRect(rectScale,paint);
            //放大
//            canvas.scale(1.2f,1.2f);
            canvas.scale(0.9f,0.9f,250,250);
        }
        //恢复现场
        canvas.restore();


        /*--------旋转坐标绘制--------*/
        //平移坐标，让接下来的图形绘制在下一个图形的上面
        canvas.translate(0,500);
        //保存现场
        canvas.save();

        canvas.drawCircle(220,200,190,paint);
        for (int i = 0; i <12 ; i++) {
            canvas.drawLine(350,200,400,200,paint);
            canvas.rotate(30,220,200);
        }

        //恢复现场
        canvas.restore();
    }
}
