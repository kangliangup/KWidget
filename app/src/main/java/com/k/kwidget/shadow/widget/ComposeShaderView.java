package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.k.kwidget.R;


public class ComposeShaderView extends View {

    private Paint paint;


    public ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        //位图渐变
        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);

        //线性渐变
        LinearGradient  linearGradient=new LinearGradient(0,0,1000,0,
                Color.RED,Color.BLUE, Shader.TileMode.CLAMP);

        //混合渐变
        ComposeShader composeShader=new ComposeShader(bitmapShader,linearGradient, PorterDuff.Mode.SRC_ATOP);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(composeShader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getMeasuredWidth(),600,paint);
    }
}
