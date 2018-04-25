package com.k.kwidget.canvas.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.k.kwidget.R;


/**
 * 用剪切区实现动画
 */
public class ClipAnimView extends View {

    //显示第n张图片
    private int position=0;

    private Bitmap bitmap;
    private RectF rectF;
    private int frameWidth;
    private int height;




    public ClipAnimView(Context context) {
        super(context);
    }

    public ClipAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.boom);

        //获取图片的高度和宽度
        int width=bitmap.getWidth();
         height=bitmap.getHeight();

        //剪切区
         frameWidth=width/7;
        rectF=new RectF(0,0,frameWidth,height);
    }

    public ClipAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //平移坐标
        canvas.translate(100,100);
        //设置剪切区
        canvas.clipRect(rectF);
        canvas.drawBitmap(bitmap,-position*frameWidth,0,null);

        canvas.restore();
        position++;
        if(position==7){
            position=0;
        }
    }
}
