package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.k.kwidget.R;


public class CirclePhotoView extends View {

    private Bitmap bitmapDog;
    private Paint paint;
    private  Bitmap bmpCircleMask;


    public CirclePhotoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapDog= BitmapFactory.decodeResource(getResources(), R.mipmap.dog);

        int width=bitmapDog.getWidth();
        int height=bitmapDog.getHeight();

        int minWidth=Math.min(width,height);
        bmpCircleMask=Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvasCircle=new Canvas(bmpCircleMask);
        int r=minWidth/2;
        canvasCircle.drawCircle(width/2,height/2,r,paint);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w=bmpCircleMask.getWidth();

        int layer=canvas.saveLayer(0,0,w,w,null,Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(bitmapDog,0,0,null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bmpCircleMask,0,0,paint);

        canvas.restoreToCount(layer);
    }
}
