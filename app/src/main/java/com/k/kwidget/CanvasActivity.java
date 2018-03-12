package com.k.kwidget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    private Canvas canvas;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        ButterKnife.bind(this);
        bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @OnClick({R.id.drawBitmap, R.id.drawPoint, R.id.drawLine, R.id.drawSquare, R.id.drawCircle, R.id.drawRoute, R.id.drawText})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.drawBitmap:
                drawBitmap();
                break;
            case R.id.drawPoint:
                break;
            case R.id.drawLine:
                break;
            case R.id.drawSquare:
                break;
            case R.id.drawCircle:
                break;
            case R.id.drawRoute:
                break;
            case R.id.drawText:
                break;
        }
    }

    private void drawBitmap() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        //原大小绘制
        canvas.drawBitmap(bmp, 0, 0, null);


        //对图片进行缩放
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        Rect src = new Rect(0, 0, width, height);
        Rect dst = new Rect(0, height, width*3, height*3+height);

        canvas.drawBitmap(bmp,src,dst,null);

        iv.setImageBitmap(bitmap);
    }
}
