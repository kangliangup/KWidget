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

/**
 * 利用这个方法，我们其实可以实现任意形状的图片，
 * 本质上，遮罩层是什么形状，图片就会 显示什么形状，
 */
public class LauncherPhotoView extends View {


    private Bitmap bitmapDog;
    private Bitmap bitmapMask;
    private Paint paint;


    public LauncherPhotoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapDog=BitmapFactory.decodeResource(getResources(), R.mipmap.dog);

        bitmapMask=BitmapFactory.decodeResource(getResources(),R.mipmap.start);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layer=canvas.saveLayer(0,0,bitmapMask.getWidth(),bitmapMask.getHeight(),
                null,Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(bitmapDog,0,0,null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapMask,0,0,paint);
//
        canvas.restoreToCount(layer);

    }
}
