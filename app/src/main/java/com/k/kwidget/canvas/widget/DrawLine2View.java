package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 用bitmap保存绘制历史,drawPath
 * 先将内容绘制在bitmap上，再统一将内容绘制在view上，可以提高绘图性能
 */
public class DrawLine2View extends View {

    private  int preX,preY;
    private Paint paint;
    private Path path;

    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;


    public DrawLine2View(Context context) {
        super(context);
    }

    public DrawLine2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        path=new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
    }

    public DrawLine2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapBuffer,0,0,null);
        canvas.drawPath(path,paint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(bitmapBuffer==null){
            int width=getMeasuredWidth();
            int height=getMeasuredHeight();
            bitmapBuffer= Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            bitmapCanvas=new Canvas(bitmapBuffer);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                path.reset();
                preX=x;
                preY=y;
                path.moveTo(x,y);
                break;

            case MotionEvent.ACTION_MOVE:
//                path.quadTo(preX,preY,x,y);

                //手指移动过程中只显示绘制过程
                // 使用贝塞尔曲线进行绘图，需要一个起点(preX,preY)
                // 一个终点(x, y)，一个控制点((preX + x)/2, (preY + y) / 2))
                int controlX = (x + preX) / 2;
                int controlY = (y + preY) / 2;
                path.quadTo(controlX, controlY, x, y);


                invalidate();
                preX=x;
                preY=y;
                break;

            case MotionEvent.ACTION_UP:
                bitmapCanvas.drawPath(path,paint);
                invalidate();
                break;

            default:
                    break;
        }

        return true;
    }
}
