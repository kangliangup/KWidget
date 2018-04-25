package com.k.kwidget.shadow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.k.kwidget.R;
import com.k.kwidget.shadow.widget.CirclePhotoView;
import com.k.kwidget.shadow.widget.GuaGuaLeView;
import com.k.kwidget.shadow.widget.LauncherPhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DemoActivity extends AppCompatActivity {


    @BindView(R.id.circle_photo_view)
    CirclePhotoView circlePhotoView;
    @BindView(R.id.launcher_photo_view)
    LauncherPhotoView launcherPhotoView;
    @BindView(R.id.gua_view)
    GuaGuaLeView guaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_circle_photo, R.id.btn_launcher_photo,R.id.btn_gua})
    public void onViewClicked(View view) {

        circlePhotoView.setVisibility(view.getId() == R.id.btn_circle_photo ? View.VISIBLE : View.GONE);
        launcherPhotoView.setVisibility(view.getId() == R.id.btn_launcher_photo ? View.VISIBLE : View.GONE);
        guaView.setVisibility(view.getId() == R.id.btn_gua ? View.VISIBLE : View.GONE);


//        switch (view.getId()) {
//            case R.id.btn_circle_photo:
//                break;
//            case R.id.circle_photo_view:
//                break;
//        }
    }
}
