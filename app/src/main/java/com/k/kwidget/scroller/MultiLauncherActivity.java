package com.k.kwidget.scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.k.kwidget.R;
import com.k.kwidget.scroller.widget.MultiLauncherView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MultiLauncherActivity extends AppCompatActivity {

    @BindView(R.id.ml)
    MultiLauncherView ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_launcher);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_pre, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pre:
                ml.moveToPre();
                break;
            case R.id.btn_next:
                ml.moveToNext();
                break;
        }
    }
}
