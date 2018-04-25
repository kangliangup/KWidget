package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 用bitmap保存绘制历史,drawLine
 * 先将内容绘制在bitmap上，再统一将内容绘制在view上，可以提高绘图性能
 */
public class DrawLine1View extends View {

    //上一个点的坐标
    private int preX,preY;

    //当前点的坐标
    private int currentX,currentY;

    private Paint paint;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;



    public DrawLine1View(Context context) {
        super(context);
    }

    public DrawLine1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);

    }

    public DrawLine1View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *组件大小发生改变时候，会回掉onSizeChanged
     * view第一次显示时候肯定会调用
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(bitmap==null){
            int width=getMeasuredWidth();
            int height=getMeasuredHeight();
            bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            bitmapCanvas=new Canvas(bitmap);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x= (int) event.getX();
        int y= (int) event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                //当手指按下时，记录第一个坐标
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动时记录当前坐标
                currentX=x;
                currentY=y;
                bitmapCanvas.drawLine(preX,preY,currentX,currentY,paint);
                invalidate();
                //当前坐标成为下一点坐标的开始
                preX=currentX;
                preY=currentY;


                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;


        }

        return true;
    }
}
