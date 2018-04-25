package com.k.kwidget.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.k.kwidget.R;
import com.k.kwidget.canvas.widget.CoordinateView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoordinateActivity extends AppCompatActivity {

    @BindView(R.id.coordinateView)
    CoordinateView coordinateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);
        ButterKnife.bind(this);
    }
}
