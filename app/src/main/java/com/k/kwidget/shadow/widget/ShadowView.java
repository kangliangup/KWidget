package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class ShadowView extends View {

    private Paint  paint;

    public ShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE,paint);

        //radius:阴影半径 x:x轴偏移量  y：y轴偏移量 color:阴影颜色
        paint.setShadowLayer(10,1,1, Color.RED);
        canvas.drawText("Android开发",100,100,paint);
        paint.setShadowLayer(10,5,5,Color.BLUE);
        canvas.drawText("Android绘图技术",100,220,paint);

    }
}
