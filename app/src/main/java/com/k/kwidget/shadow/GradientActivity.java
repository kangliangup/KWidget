package com.k.kwidget.shadow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.k.kwidget.R;
import com.k.kwidget.shadow.widget.BitmapShaderView;
import com.k.kwidget.shadow.widget.ComposeShaderView;
import com.k.kwidget.shadow.widget.FiveChessView;
import com.k.kwidget.shadow.widget.LinearGradientView;
import com.k.kwidget.shadow.widget.RadialGradientView;
import com.k.kwidget.shadow.widget.SweepGradientView;
import com.k.kwidget.shadow.widget.SweepMatrixView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 渐变
 */
public class GradientActivity extends AppCompatActivity {

    @BindView(R.id.line_gradient)
    LinearGradientView lineGradient;
    @BindView(R.id.radial_view)
    RadialGradientView radialView;
    @BindView(R.id.five_view)
    FiveChessView fiveView;
    @BindView(R.id.sweep_view)
    SweepGradientView sweepView;
    @BindView(R.id.bitmap_view)
    BitmapShaderView bitmapView;
    @BindView(R.id.compose_view)
    ComposeShaderView composeView;
    @BindView(R.id.sweep_matrix_view)
    SweepMatrixView sweepMatrixView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_line, R.id.btn_radial, R.id.btn_five, R.id.btn_sweep,
            R.id.btn_bitmap,R.id.btn_compose,R.id.btn_sweep_matrix})
    public void onViewClicked(View view) {

        lineGradient.setVisibility(view.getId() == R.id.btn_line ? View.VISIBLE : View.GONE);
        radialView.setVisibility(view.getId() == R.id.btn_radial ? View.VISIBLE : View.GONE);
        fiveView.setVisibility(view.getId() == R.id.btn_five ? View.VISIBLE : View.GONE);
        sweepView.setVisibility(view.getId() == R.id.btn_sweep ? View.VISIBLE : View.GONE);
        bitmapView.setVisibility(view.getId() == R.id.btn_bitmap ? View.VISIBLE : View.GONE);
        composeView.setVisibility(view.getId() == R.id.btn_compose ? View.VISIBLE : View.GONE);
        sweepMatrixView.setVisibility(view.getId() == R.id.btn_sweep_matrix ? View.VISIBLE : View.GONE);

        switch (view.getId()) {
            case R.id.btn_line:
                break;
            case R.id.btn_radial:
                break;
        }
    }
}
