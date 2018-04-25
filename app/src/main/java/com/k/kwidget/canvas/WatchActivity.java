package com.k.kwidget.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.k.kwidget.R;
import com.k.kwidget.canvas.widget.WatchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchActivity extends AppCompatActivity {

    @BindView(R.id.watchView)
    WatchView watchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        ButterKnife.bind(this);
        watchView.run();
    }
}
