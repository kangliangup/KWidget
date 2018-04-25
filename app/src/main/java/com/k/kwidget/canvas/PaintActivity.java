package com.k.kwidget.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.k.kwidget.R;
import com.k.kwidget.util.DensityUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaintActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ButterKnife.bind(this);

        //创建bitmap
        Bitmap bitmap=Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);

        //创建canvas
        Canvas canvas=new Canvas(bitmap);

        //创建paint
        Paint paint = new Paint();


       /*--------paint 颜色相关属性--------*/
        //设置颜色
        paint.setColor(Color.RED);
        //设置颜色和透明度
//        paint.setARGB(80,0,0,0);
        //设置透明度
//        paint.setAlpha(125);


        /*--------paint 文本相关--------*/
        //设置文字大小
        paint.setTextSize(20);
        //设置文本对齐方式
        paint.setTextAlign(Paint.Align.LEFT);
        //设置文本倾斜
        paint.setTextSkewX(-0.5f);
        //添加下划线
        paint.setUnderlineText(true);
        //设置为粗体
        paint.setFakeBoldText(true);
        //添加删除线
        paint.setStrikeThruText(true);

        //绘制文本
        canvas.drawText("hello world",10,100,paint);


        //重制属性
        paint.reset();
        paint.setColor(Color.BLUE);


        /*--------paint 图形相关--------*/
        //设置为空心、实心、空心和实心
        paint.setStyle(Paint.Style.STROKE);
        //设置边框宽度
        paint.setStrokeWidth(10);
        //当style为stroke时,设置角的样式
        paint.setStrokeJoin(Paint.Join.ROUND);
        //设置落笔时的样式
        paint.setStrokeCap(Paint.Cap.SQUARE);
        //抗锯齿
        paint.setAntiAlias(true);

        //绘制一个矩形
        canvas.drawRect(new Rect(10,200,350,350),paint);

        //将bitmap显示在iv上
        iv.setImageBitmap(bitmap);



    }
}
