package com.k.kwidget.scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.k.kwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 滚动方向与坐标系相反
 * textview滚动实际是内容的滚动
 * linearlayout滚动实际是子组件的滚动
 */
public class ScrollToByActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_to_by);
        ButterKnife.bind(this);




    }

    @OnClick({R.id.btn_scroll_to, R.id.btn_scroll_by})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //滚动方向与坐标系相反
            case R.id.btn_scroll_to:
                int x = (int) tv.getX();
                int y = (int) tv.getY();
//                tv.scrollTo(x - 20, y);
                linearLayout.scrollTo(x - 20, y);
                break;
            case R.id.btn_scroll_by:

//                tv.scrollBy(-20, 0);
                linearLayout.scrollBy(-20, 0);
                break;
        }
    }
}
