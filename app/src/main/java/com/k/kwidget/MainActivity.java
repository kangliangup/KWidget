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


    }
}
