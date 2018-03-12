package com.k.kwidget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.k.kwidget.util.DensityUtils;

public class MainActivity extends AppCompatActivity {

    /**
     * 1、继承自 View 的组件必须重写哪些方法？继承自 ViewGroup 的组件必须重写哪些方法？
     * 继承View： onMeasure，onDraw
     * 继承ViewGroup: onMeasure，onDraw，onLayout
     * <p>
     * 2、Graphics
     * Point和PointF
     * Rect和RectF
     * Bitmap和BitmapDrawable
     * Canvas和Paint
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.iv);
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);


        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);


        //绘制文字
        //抗锯齿
        paint.setAntiAlias(true);

        //设置文字大小
        paint.setTextSize(DensityUtils.sp2px(this, 16));

        //FILL实心 STROKE空心 FILL_AND_STROKE同时使用实心和空心
        paint.setStyle(Paint.Style.FILL);

        //设置文字对其
        paint.setTextAlign(Paint.Align.LEFT);

        //设置文本的倾斜程度，skewX 取值于 0~1 之间，正负表示倾斜的方向。
        paint.setTextSkewX(0.5f);

        //设置下划线
        paint.setUnderlineText(true);

        //设置粗体
        paint.setFakeBoldText(true);


        canvas.drawText("测试文字", 10, 100, paint);


        //绘制图形
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.ROUND);//请修改枚举值查看效果

        //绘制一个矩形
        canvas.drawRect(new Rect(20, 200, 350, 350), paint);

        imageView.setImageBitmap(bitmap);

    }
}
