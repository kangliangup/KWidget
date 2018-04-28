package com.k.kwidget.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.k.kwidget.R;
import com.k.kwidget.custom.widget.SliderMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SligingMenuActivity extends AppCompatActivity {


    @BindView(R.id.toggle)
    Button toggle;
    @BindView(R.id.sliding_menu)
    SliderMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliging_menu);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.toggle)
    public void onViewClicked() {
        slidingMenu.toggle();
    }
}
