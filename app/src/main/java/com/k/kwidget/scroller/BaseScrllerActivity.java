package com.k.kwidget.scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.k.kwidget.R;
import com.k.kwidget.scroller.widget.BaseScrollerViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseScrllerActivity extends AppCompatActivity {

    @BindView(R.id.group)
    BaseScrollerViewGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_scrller);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                group.start();
                break;
            case R.id.btn_stop:
                group.abort();
                break;
        }
    }
}
