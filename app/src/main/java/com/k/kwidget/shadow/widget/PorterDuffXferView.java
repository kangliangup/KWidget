package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class PorterDuffXferView extends View {

    private Bitmap b3;

    public PorterDuffXferView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Bitmap dst=Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_8888);
        Bitmap src=dst.copy(Bitmap.Config.ARGB_8888,true);
        b3=Bitmap.createBitmap(450,450, Bitmap.Config.ARGB_8888);

        Canvas  canvasDst=new Canvas(dst);
        Canvas  canvasSrc=new Canvas(src);
        Canvas  c3=new Canvas(b3);

        Paint paintDst=new Paint();
        paintDst.setColor(Color.GRAY);
        canvasDst.drawCircle(150,150,150,paintDst);


        Paint paintSrc=new Paint();
        paintSrc.setColor(Color.GREEN);
        canvasSrc.drawRect(0,0,300,300,paintSrc);



        Paint paint=new Paint();

        //创建图层
        int layer=c3.saveLayer(150,150,450,450,null,Canvas.ALL_SAVE_FLAG);

        //画圆
        c3.drawBitmap(dst,0,0,null);
        //定义位图的运算模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        //画矩形
        c3.drawBitmap(src,150,150,paint);
        //清楚运算结果
        paint.setXfermode(null);

        //恢复
        c3.restoreToCount(layer);




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(b3,0,0,null);
    }
}
