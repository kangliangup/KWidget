package com.k.kwidget.canvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.k.kwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasActivity extends AppCompatActivity {

    // 图形用户界面（Graphical User Interface，简称 GUI，又称图形用户接口)

    @BindView(R.id.iv)
    ImageView iv;
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        ButterKnife.bind(this);

        bitmap=Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint=new Paint();
    }

    /**
     * 清屏，清除之前的绘制
     */
    private void clear(){
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }


    @OnClick({R.id.drawBitmap, R.id.drawPoint, R.id.drawLine, R.id.drawSquare, R.id.drawCircle, R.id.drawRoute,
            R.id.drawText,R.id.drawBezier})
    public void onViewClicked(View view) {

        clear();
        paint.reset();

        switch (view.getId()) {
            case R.id.drawBitmap:
                drawBitmap();
                break;
            case R.id.drawPoint:
                drawPoint();
                break;
            case R.id.drawLine:
                drawLine();
                break;
            case R.id.drawSquare:
                drawSquare();
                break;
            case R.id.drawCircle:
                drawCircle();
                break;
            case R.id.drawRoute:
                drawRoute();
                break;
            case R.id.drawBezier:
                drawBezier();
                break;
            case R.id.drawText:
                drawText();
                break;
        }

        iv.setImageBitmap(bitmap);
    }

    /**
     * 绘制文字,沿着路径绘制
     */
    private void drawText() {

        paint.setTextSize(20);
        String text="好好学习天天向上";
        canvas.drawText(text,10,50,paint);
        canvas.drawText(text,0,4,10,100,paint);

        Path path=new Path();
        path.moveTo(10,200);
        path.quadTo(100,100,300,300);
        canvas.drawTextOnPath(text,path,15,15,paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path,paint);


    }

    /**
     * 绘制贝塞尔曲线
     */
    private void drawBezier() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        Path path=new Path();

         /*--------2阶段贝塞尔曲线--------*/
        path.moveTo(50,50);
        path.quadTo(150,10,200,200);
        canvas.drawPath(path,paint);

        //画点（100，100）（200，10）（300，300）
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        canvas.drawPoints(new float[]{50,50,150,10,200,200},paint);

        /*--------3阶段贝塞尔曲线--------*/
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path.reset();
        path.moveTo(50,250);
        path.cubicTo(150,260,200,150,400,400);
        canvas.drawPath(path, paint);

        //画点
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);
        canvas.drawPoints(new float[]{50,250,150,260,200,150,400,400},paint);


        /*--------弧线--------*/
        paint.reset();
        path.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path.moveTo(250,100);
        //forceMoveTo为true时开始一个新的图形 不和上一个点拼接
        path.arcTo(new RectF(250,150,450,300),-30,60,false);
//        path.addOval(new RectF(250,150,450,300),Path.Direction.CW);
        canvas.drawPath(path,paint);

    }

    /**
     * 绘制路径
     */
    private void drawRoute() {

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        //抗锯齿
        paint.setAntiAlias(true);
        Path path=new Path();

        //将画笔移动到（x,y）位置
        path.moveTo(10,75);
        path.rLineTo(150,0);
        path.rLineTo(-150,75);
        path.rLineTo(75,-150);
        path.rLineTo(75,150);
        //第一个点和最后一个点用直线连接起来,形成回路
        path.close();

        //矩形,CCW逆时针
        path.addRect(new RectF(10,180,110,250),Path.Direction.CCW);
        //圆角矩形,4 个角的弧度都不一样，2 个数确定一个弧度
        path.addRoundRect(new RectF(10,260,110,330),new float[]{10, 10, 20, 10, 30, 40, 40, 30},Path.Direction.CCW);
        //椭圆
        path.addOval(new RectF(10,350,110,400), Path.Direction.CW);
        //圆
        path.addCircle(60,440,30, Path.Direction.CW);
        //弧线
        path.addArc(new RectF(10,480,110,550),-30,-60);

        canvas.drawPath(path,paint);

    }

    /**
     * 绘制圆、椭圆、弧线、扇形
     */
    private void drawCircle() {

        //抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setTextSize(16);


        //绘制一个椭圆
        canvas.drawText("绘制一个椭圆",10,20,paint);
        canvas.drawOval(new RectF(10,30,150,100),paint);

        //绘制一个圆
        canvas.drawText("绘制一个圆",10,130,paint);
        canvas.drawCircle(60,190,50,paint);

        //绘制弧线
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GRAY);
        canvas.drawOval(new RectF(10,250,110,350),paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(new RectF(10,250,110,350),-30,-50,false,paint);

        //绘制扇形
//        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GRAY);
        canvas.drawOval(new RectF(10,360,110,450),paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(new RectF(10,360,110,450),-30,-50,true,paint);

    }


    /**
     * 绘制矩形
     */
    private void drawSquare() {
        paint.setColor(Color.BLUE);
        paint.setTextSize(16);

        //绘制一个实心矩形
        canvas.drawText("绘制实心矩形",10,20,paint);
        Rect rect= new Rect(10,30,100,100);
        canvas.drawRect(rect,paint);

        //绘制一个空心圆角矩形
        canvas.drawText("绘制圆角空心矩形",10,120,paint);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRoundRect( new RectF(10,130,100,200),20,20,paint);

    }

    /**
     * 画线,绘制多条和绘制点方式差不多
     */
    private void drawLine() {
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);

        //绘制一条红色的线
        canvas.drawLine(0,0,200,0,paint);
    }

    /**
     * 绘制点
     */
    private void drawPoint() {

        //设置点的大小
        paint.setStrokeWidth(10);

        paint.setTextSize(16);
        canvas.drawText("画一个红色的点",10,20,paint);
        paint.setColor(Color.RED);

        //画一个红色的点
        canvas.drawPoint(10,30,paint);

        //两个数一组画四个蓝色的点，每两个数
        paint.setColor(Color.BLUE);
        canvas.drawText("画四个蓝色的点",10,80,paint);
        float[] pointsBlue=new float[]{50,100,50,150,50,200,50,250};
        canvas.drawPoints(pointsBlue,paint);

    }

    /**
     * 绘制位图
     */
    private void drawBitmap() {

        Bitmap bmp=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        int margin=20;

        paint.setTextSize(16);
        canvas.drawText("原图绘制",10,20,paint);

        /*--------原大小绘制--------*/
        canvas.drawBitmap(bmp,0,margin,null);

        /*--------对图片进行缩放--------*/
        int bmpWidth=bmp.getWidth();
        int bmpHeight=bmp.getHeight();
        canvas.drawText("缩放绘制",10,20+margin+bmpHeight,paint);

        Rect src=new Rect(0,0,bmpWidth,bmpHeight);
        Rect dst=new Rect(0,bmpHeight+margin,bmpWidth*3,bmpHeight*3+bmpHeight+margin);
        canvas.drawBitmap(bmp,src,dst,null);


    }
}
