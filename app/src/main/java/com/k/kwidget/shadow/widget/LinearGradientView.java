package com.k.kwidget.shadow.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 线性渐变,警告:Avoid object allocations during draw/layout operations (preallocate and reuse instead)
 * 意思是不要在view绘制和做布局操作的时候实例化数据,即不要在自定义View的onMeasure、onLayout、onDraw等方法里面做new对象的操作。
 */
public class LinearGradientView extends View {

    private Paint paint;
    private Rect rect1;
    private Rect rect2;
    private Rect rect3;
    private LinearGradient lg1;
    private LinearGradient lg2;
    private LinearGradient lg3;


    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);


        rect1=new Rect(100,100,500,400);
        lg1=new LinearGradient(rect1.left,rect1.top,rect1.right,rect1.bottom,
                Color.RED,Color.BLUE, Shader.TileMode.CLAMP);

        //放大渐变矩形
        rect2=new Rect(rect1);
        rect2.inset(-100,-100);
        lg2=new LinearGradient(rect2.left,rect2.top,rect2.right,rect2.bottom,
                Color.RED,Color.BLUE, Shader.TileMode.CLAMP);

        //缩小渐变矩形
        rect3=new Rect(rect1);
        rect3.inset(100,100);
        lg3=new LinearGradient(rect3.left,rect3.top,rect3.right,rect3.bottom,
                Color.RED,Color.BLUE, Shader.TileMode.CLAMP);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setShader(lg1);
        canvas.drawRect(rect1,paint);

        //坐标往下移动
        canvas.translate(0, rect1.height() + 100);
////       //放大渐变矩形
//        rect2.inset(-100, -100);
        paint.setShader(lg2);
        canvas.drawRect(rect1, paint);


        //坐标往下移动
        canvas.translate(0, rect1.height() + 100);
////        //缩小渐变矩形
//        rect3.inset(-100, -100);
        paint.setShader(lg3);
        canvas.drawRect(rect1, paint);

    }
}
