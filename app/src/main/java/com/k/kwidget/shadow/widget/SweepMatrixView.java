package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class SweepMatrixView extends View {

    private Paint paint;
    private Shader shader;
    private Matrix matrix;
    private float mRotate;

    public SweepMatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        matrix=new Matrix();

        setFocusable(true);
        setFocusableInTouchMode(true);

        float x=160;
        float y=160;

        shader=new SweepGradient(x,y,new int[]{
                Color.RED,Color.BLUE,Color.GREEN
        },null);
        paint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(300,300);
        canvas.drawColor(Color.WHITE);
        matrix.setRotate(mRotate,160,160);
        shader.setLocalMatrix(matrix);
        mRotate+=3;
        if(mRotate>=360){
            mRotate=0;
        }
        invalidate();
        canvas.drawCircle(160,160,200,paint);

    }
}
