package com.k.kwidget.custom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class CustomTextView extends View {

    public static final String TEXT="好好学习，天天向上";

    private Paint paint;

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(100);
        paint.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //将文字放在最中间
        Rect textRect=getTextRect();
        int viewWidth=getMeasuredWidth();
        int viewHeight=getMeasuredHeight();

        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        int x=(viewWidth-textRect.width())/2;
        int y = (int) (viewHeight / 2 +
                (fontMetrics.descent- fontMetrics.ascent) / 2
                - fontMetrics.descent);

        canvas.drawText(TEXT,x,y,paint);

    }

    /**
     * 获取文字所占的尺寸
     */
    private Rect getTextRect(){

        Rect rect=new Rect();
        //文字所占的区域大小保存在rect中
        paint.getTextBounds(TEXT,0,TEXT.length(),rect);

        return rect;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Rect rect=getTextRect();
        int textWidth=rect.width();
        int textHeight=rect.height();
        int width=measureWidth(widthMeasureSpec,textWidth);
        int height=measureHeight(heightMeasureSpec,textHeight);

        setMeasuredDimension(width,height);

    }

    private int measureHeight(int heightMeasureSpec, int textHeight) {

        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        int height=0;

        if(mode==MeasureSpec.EXACTLY){
            //宽度为match和具体值时,直接将size作为组件的宽度
            height=size;
        }
        else if(mode==MeasureSpec.AT_MOST){
            //宽度为wrap_content，宽度需要计算，此处为文字宽度
            height=textHeight;
        }

        return height;
    }

    private int measureWidth(int widthMeasureSpec, int textWidth) {
        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);
        int width=0;

        if(mode==MeasureSpec.EXACTLY){
            width=size;
        }
        else if(mode==MeasureSpec.AT_MOST){
            width=textWidth;
        }

        return width;
    }


}
