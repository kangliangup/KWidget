package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 直接在canvas中drawPath
 */
public class DrawLine3View extends View {

    private  int preX,preY;
    private Paint paint;
    private Path path;


    public DrawLine3View(Context context) {
        super(context);
    }

    public DrawLine3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        path=new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
    }

    public DrawLine3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
//                path.reset();
                preX=x;
                preY=y;
                path.moveTo(x,y);
                break;

            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                invalidate();
                preX=x;
                preY=y;
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                    break;
        }

        return true;
    }
}
