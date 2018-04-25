package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.k.kwidget.R;

import java.util.Random;


/**
 * 刮刮乐
 */
public class GuaGuaLeView extends View {

    private static  final  String[] results={
        "恭喜，您中了一等奖，奖金 1 亿元",
        "恭喜，您中了二等奖，奖金 5000 万元",
        "恭喜，您中了三等奖，奖金 100 元",
        "很遗憾，您没有中奖，继续加油哦"
    };

    /**
     * 涂抹的粗细
     */
    public static final int FINGER=100;

    private Paint paint;
    private Paint clearPaint;

    private  float preX,preY;

    private Bitmap bitmapBuffer;
    private Canvas canvasBuffer;

    private Random random;

    /**
     * 生成随机中奖信息
     * @return string
     */
    public String getRandomResult(){
        return  results[random.nextInt(results.length)];
    }

    public GuaGuaLeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        random=new Random();
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);

        clearPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        clearPaint.setStrokeJoin(Paint.Join.ROUND);
        clearPaint.setStrokeCap(Paint.Cap.ROUND);
        clearPaint.setStrokeWidth(FINGER);

        drawBackground();
    }

    /**
     * 画背景
     */
    private void drawBackground() {

       //获取图片资源bitmap
       Bitmap bitmapBakground = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
       //从资源中获取的bitmap是不可修改的，需要复制出一张可以修改的图片
        Bitmap bmpBacgroundMutable=bitmapBakground.copy(Bitmap.Config.ARGB_8888,true);
        //在图片上画中奖信息
        Canvas canvas=new Canvas(bmpBacgroundMutable);

        //获取中奖信息
        String text=getRandomResult();

        //计算出文字所占的区域，将文字放在正中间
        Rect rect=new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        int x=(bmpBacgroundMutable.getWidth()-rect.width())/2;
        int y=(bmpBacgroundMutable.getHeight()-rect.height())/2;

        //设置阴影
        setLayerType(View.LAYER_TYPE_SOFTWARE,paint);
        paint.setShadowLayer(10,0,0,Color.GREEN);

        //绘制文字
        canvas.drawText(text,x,y,paint);
        paint.setShadowLayer(0, 0, 0, Color.YELLOW);


        setBackground(new BitmapDrawable(getResources(),bmpBacgroundMutable));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapBuffer,0,0,paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


            //初始化缓冲区
        //TODO: h为什么为0
            bitmapBuffer=Bitmap.createBitmap(w,2000, Bitmap.Config.ARGB_8888);
            canvasBuffer=new Canvas(bitmapBuffer);
            //设置缓冲区颜色
            canvasBuffer.drawColor(Color.parseColor("#FF808080"));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x=event.getX();
        float y=event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                preX=x;
                preY=y;
                break;

            case MotionEvent.ACTION_MOVE:
                canvasBuffer.drawLine(preX,preY,x,y,clearPaint);
                invalidate();
                preX=x;
                preY=y;
                break;

            case MotionEvent.ACTION_UP:
                invalidate();
                break;


        }



        return true;
    }
}
