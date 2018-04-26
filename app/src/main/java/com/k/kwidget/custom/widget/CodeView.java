package com.k.kwidget.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.k.kwidget.R;
import com.k.kwidget.util.DensityUtils;

import java.util.Random;


/**
 * * 主要功能
 *改变字体大小
 *改变字体颜色
 *获取当前验证码
 *改变干挠线个数
 *刷新验证码
 *改变验证码个数
 */
public class CodeView extends View {

    private int lineCount;//干扰线条数
    private int codeCount;//验证码个数
    private int fontSize;//验证码字体大小
    private int fontColor;//验证码字体颜色

    public static final int DEFAULT_LINE_COUNT=50;//默认干扰线条数
    public static final int DEFAULT_CODE_COUNT=4;//默认验证码个数
    public static final int DEFAULT_FONT_SIZE=14;//默认字体个数
    public static final int DEFAULT_FONT_COLOR= Color.BLACK;//默认验证码字体颜色

    private Random random;
    private Paint paint;
    private String code;

    private static final String TAG = "CodeView";


    public CodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CodeView);
        lineCount=typedArray.getInt(R.styleable.CodeView_line_count,DEFAULT_LINE_COUNT);
        codeCount=typedArray.getInt(R.styleable.CodeView_num_count,DEFAULT_CODE_COUNT);
        fontColor=typedArray.getColor(R.styleable.CodeView_code_color,DEFAULT_FONT_COLOR);
        fontSize=  DensityUtils.sp2px(context,
                typedArray.getDimensionPixelOffset(R.styleable.CodeView_font_size,DEFAULT_FONT_SIZE));
        typedArray.recycle();

        random=new Random();
        paint=new Paint();
        initPaint();
        code=getCode();

    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(fontColor);
        paint.setTextSize(fontSize);
    }

    /**
     * 获取随机验证码
     */
    private String getCode(){
        StringBuilder str= new StringBuilder();
        for (int i = 0; i <codeCount ; i++) {
            str.append(random.nextInt(10));
        }

        return str.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Rect textRect=getTextRect();
        int width=measureWidth(widthMeasureSpec,textRect);
        int height=measureHeight(heightMeasureSpec,textRect);

        setMeasuredDimension(width,height);

    }

    /**
     * 计算组件的宽度
     */
    private int measureWidth(int widthMeasureSpec,Rect textRect){

        int wid=textRect.width();

        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);
        int width=0;

        if(mode==MeasureSpec.EXACTLY){
            width=size;
        }
        else if(mode==MeasureSpec.AT_MOST){
            width=getPaddingLeft()+getPaddingRight()+textRect.width();
        }
        return width;

    }

    /**
     *计算组件的高度
     */
    private int measureHeight(int heightMeasureSpec,Rect textRect){

        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        int height=0;

        if(mode==MeasureSpec.EXACTLY){
            height=size;
        }
        else if(mode==MeasureSpec.AT_MOST){
            height=getPaddingBottom()+getPaddingTop()+textRect.height();
        }

        return height;
    }

    /**
     * 获取文字所占的尺寸
     */
    private Rect getTextRect(){

        Rect rect=new Rect();
        //文字所占的区域大小保存在rect中
        paint.getTextBounds(code,0,code.length(),rect);

        return rect;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height=getMeasuredHeight();
        int width=getMeasuredWidth();
        paint.setTextSize(fontSize);

            Rect rect=new Rect(0,0,width,height);

        //========1、绘制边框========
        //空心边框
        paint.setColor(fontColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        Rect rectBorder=new Rect(rect);

        rectBorder.inset(5,5);//缩小一点
        canvas.drawRect(rectBorder,paint);
        //还原成实心
        paint.setStyle(Paint.Style.FILL);


        //========2、绘制干扰线========
        paint.setColor(Color.GRAY);
        for (int i = 0; i <lineCount ; i++) {
            int x1=random.nextInt(width-5);
            int x2=random.nextInt(width-5);
            int y1=random.nextInt(height-5);
            int y2=random.nextInt(height-5);
            canvas.drawLine(x1,y1,x2,y2,paint);
        }
        paint.setColor(fontColor);

        //========3、绘制文字========
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int x = (width - getTextRect().width()) / 2;
        int y = (int) (height / 2 +
                (fontMetrics.descent- fontMetrics.ascent) / 2
                - fontMetrics.descent);
        canvas.drawText(code, x, y, paint);
    }

    /**
     * 刷新验证码
     */
    public void refreshCode(){
        code=getCode();
        invalidate();
    }

    /**
     * 设置颜色
     */
    public void setColor(int color){
        fontColor=color;
        invalidate();
    }


    /**
     * 设置文字大小
     */
    public void setFontSize(int size){
        fontSize=size;
//        invalidate();
        initPaint();
        requestLayout();
    }

    public void setCodeCount(int count){
        codeCount=count;
        code=getCode();
//        invalidate();
        //重新调整布局大小
        requestLayout();
    }

    public void setLineCount(int count){
        this.lineCount=count;
        invalidate();
    }
}
