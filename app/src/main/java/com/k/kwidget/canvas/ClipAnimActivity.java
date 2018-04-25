package com.k.kwidget.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.k.kwidget.R;
import com.k.kwidget.canvas.widget.ClipAnimView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClipAnimActivity extends AppCompatActivity {

    @BindView(R.id.clip)
    ClipAnimView clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_anim);
        ButterKnife.bind(this);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                clip.postInvalidate();
            }
        },200,200);
    }
}
