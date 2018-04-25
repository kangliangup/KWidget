package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class RadialGradientView extends View {

    private Paint paint;
    private RectF rectF;
    private RadialGradient rg;


    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF=new RectF(100,100,500,500);
        rg=new RadialGradient(300,300,200,
                Color.RED,Color.GREEN, Shader.TileMode.MIRROR);
        paint.setShader(rg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rectF,paint);

        canvas.translate(550,0);
        canvas.drawOval(rectF,paint);
    }
}
