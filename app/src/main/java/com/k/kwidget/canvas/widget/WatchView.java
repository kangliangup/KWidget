package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 手表实例
 */
public class WatchView extends View {


    private Paint paint;

    private Calendar calendar;


    public WatchView(Context context) {
        super(context);
    }

    public WatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //去锯齿
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);

        calendar=Calendar.getInstance();

        paint.setStyle(Paint.Style.STROKE);

    }

    public WatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getMeasuredWidth();
        int r=width/2;



        /*--------绘制外面的圆--------*/
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(5);
        canvas.drawCircle(width/2,width/2,width/2,paint);
        canvas.drawCircle(width/2,width/2,20,paint);


        /*--------绘制刻度线--------*/
        canvas.save();
        for (int i = 0; i <60 ; i++) {
            //长刻度
            if(i%5==0){
                paint.setColor(Color.RED);
                paint.setStrokeWidth(8);
                canvas.drawLine(0,r,100,r,paint);
            }
            //短刻度
            else{
                paint.setStrokeWidth(5);
                paint.setColor(Color.LTGRAY);
                canvas.drawLine(0,r,60,r,paint);
            }

            canvas.rotate(6,r,r);
        }
        canvas.restore();



        /*--------绘制指针--------*/

        //获取时分秒
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hours=calendar.get(Calendar.HOUR_OF_DAY);
        int minutes=calendar.get(Calendar.MINUTE);
        int seconds=calendar.get(Calendar.SECOND);


        //画时间针
        canvas.save();
        paint.setStrokeWidth(15);
        paint.setColor(Color.BLACK);
        canvas.rotate(360*hours/12,r,r);
        canvas.drawLine(r,r+60,r,r/2,paint);
        canvas.restore();

        //画分针
        canvas.save();
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        canvas.rotate(360f*minutes/60+seconds*6f/60,r,r);
        canvas.drawLine(r,r+60,r,r/2-100,paint);
        canvas.restore();

        //画秒针
        canvas.save();
        paint.setStrokeWidth(8);
        paint.setColor(Color.GREEN);
        canvas.rotate(360*seconds/60,r,r);
        canvas.drawLine(r,r+60,r,60,paint);
        canvas.restore();

    }

    /**
     * 开始动画
     */
    public void run(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        },0,1000);
    }


}
