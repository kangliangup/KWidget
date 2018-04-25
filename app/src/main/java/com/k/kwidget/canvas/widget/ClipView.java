package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.k.kwidget.R;


public class ClipView extends View {
    private  Bitmap bitmap;
    private  RectF rectF;


    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
         bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
         rectF=new RectF(100,100,500,500);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /* --------原图展示--------*/
        //绘制狗狗完整照片
        canvas.drawBitmap(bitmap,0,0,null);


        /* --------剪切区--------*/
        //平移坐标，让下面的图显示在上面图的下方
        canvas.translate(0,500);
        canvas.save();
        //定义剪切区
        canvas.clipRect(rectF);
        //再次绘制狗狗照片
        canvas.drawBitmap(bitmap,0,0,null);
        canvas.restore();


        /* --------剪切区运算--------*/
        //平移坐标，让下面的图显示在上面图的下方
        canvas.translate(0,500);
        canvas.save();
//        //定义剪切区
        canvas.clipRect(rectF);
        canvas.drawBitmap(bitmap,0,0,null);
        //定义一个新的剪切区
        Path path=new Path();
        path.addCircle(500,350,200,Path.Direction.CW);
        //剪切运算
        canvas.clipPath(path, Region.Op.UNION);
//        //再次绘制狗狗照片
        canvas.drawBitmap(bitmap,0,0,null);
        canvas.restore();


    }
}
