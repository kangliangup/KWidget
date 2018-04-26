package com.k.kwidget.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


import com.k.kwidget.R;


public class CircleImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint paint;
    private int border;
    private int borderColor;
    private Xfermode xfermode;
    private Path path=new Path();

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        xfermode=new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        path=new Path();

        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        border=array.getDimensionPixelSize(R.styleable.CircleImageView_circle_border_width,0);
        borderColor=array.getColor(R.styleable.CircleImageView_circle_border_color, Color.GRAY);
        array.recycle();

    }

    private RectF ovalRect;

    private Rect rect;

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable mDrawable=getDrawable();
        if(mDrawable==null){
            super.onDraw(canvas);
            return;
        }

        int width=getMeasuredWidth();
        int height=getMeasuredHeight();


        if(ovalRect==null){
            ovalRect=new RectF(0,0,width,height);
        }



            int layer=canvas.saveLayer(getPaddingLeft(),getPaddingTop(),width,height,null,Canvas.ALL_SAVE_FLAG);

//        Drawable对象转化为bitmap
        Bitmap bitmap=((BitmapDrawable)mDrawable).getBitmap();

        if(rect==null){
            rect= new Rect(0,0,mDrawable.getIntrinsicWidth(),mDrawable.getIntrinsicHeight());
        }

        canvas.drawBitmap(bitmap,rect, ovalRect,null);

        paint.setXfermode(xfermode);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        path.reset();

        path.addOval(ovalRect, Path.Direction.CCW);
        canvas.drawPath(path,paint);
        paint.setXfermode(null);

        canvas.restoreToCount(layer);

        //画空心圆
        if(border!=0){
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            paint.setStrokeWidth(border);
            ovalRect.inset(border/2,border/2);
            canvas.drawOval(ovalRect,paint);

        }


    }
}
