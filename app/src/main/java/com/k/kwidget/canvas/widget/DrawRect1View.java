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
 * 绘制矩形
 */
public class DrawRect1View extends View{

    private int firstX,firstY;

    private Paint paint;
    private Path path;
    private Bitmap bitmapBuffer;
    private Canvas canvasBuffer;


    public DrawRect1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        path=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
        canvas.drawBitmap(bitmapBuffer,0,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x=(int)event.getX();
        int y=(int)event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                path.reset();
                firstX=x;
                firstY=y;
                break;

            case MotionEvent.ACTION_MOVE:

                path.reset();

                //↘方向
                if(x>firstX&&y>firstY){
                    path.addRect(firstX,firstY,x,y,Path.Direction.CW);
                }

                //↗方向
                else if(x>firstX&&y<firstY){
                    path.addRect(firstX,y,x,firstY,Path.Direction.CW);
                }

                //↖方向
                else if(x<firstX&&y>firstY){
                    path.addRect(x,firstY,firstX,y,Path.Direction.CW);
                }


                //↙方向
                else if(x<firstX&&y<firstY){
                    path.addRect(x,y,firstX,firstY,Path.Direction.CW);
                }

                else {
                   break;
                }




                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                canvasBuffer.drawPath(path,paint);
                invalidate();
                break;

            default:
                break;



        }

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(bitmapBuffer==null){
            int width=getMeasuredWidth();
            int height=getMeasuredHeight();
            bitmapBuffer=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            canvasBuffer=new Canvas(bitmapBuffer);
        }

    }
}
