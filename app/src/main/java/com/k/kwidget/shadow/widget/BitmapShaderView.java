package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.k.kwidget.R;


public class BitmapShaderView extends View {

    private Bitmap bitmap;
    private Paint paint;

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        BitmapShader bs=new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        paint.setShader(bs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint);

    }
}
