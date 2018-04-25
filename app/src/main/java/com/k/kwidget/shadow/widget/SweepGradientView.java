package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 扫描渐变
 */
public class SweepGradientView extends View {

    private Paint paint;
    private SweepGradient sg1;
    private SweepGradient sg2;

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);

        //两种颜色渐变
        sg1=new SweepGradient(200,200, Color.RED,Color.BLUE);

        //多种颜色渐变
        sg2=new SweepGradient(200,200,
                new int[]{Color.RED,Color.BLUE,Color.GREEN,Color.RED},null);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setShader(sg1);
        canvas.drawCircle(200,200,200,paint);

        canvas.translate(0,450);
        paint.setShader(sg2);
        canvas.drawCircle(200,200,200,paint);
    }
}
