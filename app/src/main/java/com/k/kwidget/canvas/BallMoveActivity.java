package com.k.kwidget.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.k.kwidget.R;
import com.k.kwidget.canvas.widget.BallMoveView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Graphic动画
 * invalidate()：更新ui，实现动画效果
 * postInvalidate()：在子线程中用
 */
public class BallMoveActivity extends AppCompatActivity {

    @BindView(R.id.ball)
    BallMoveView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_move);
        ButterKnife.bind(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ball.postInvalidate();
            }
        },200,50);
    }
}
