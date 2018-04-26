package com.k.kwidget.custom;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.FontsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.k.kwidget.R;
import com.k.kwidget.custom.widget.CodeView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends AppCompatActivity {

    @BindView(R.id.code_view)
    CodeView codeView;
    private Random random;

    public static final int[] COLORS={
        Color.RED,Color.BLACK,Color.GREEN,Color.YELLOW,Color.GRAY};
    public static final int[] FONT_SIZES={
            50,100,150,200,250};
    public static final int[] CODE_COUNT={
            3,4,5,6};
    public static final int[] LINE_COUNT={
            30,60,90,120};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ButterKnife.bind(this);
        random=new Random();

    }

    @OnClick({R.id.btn_refresh, R.id.btn_change_color, R.id.btn_change_text_size,
            R.id.btn_change_num_count, R.id.btn_change_line_count})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:
               codeView.refreshCode();
                break;
            case R.id.btn_change_color:
                int position=random.nextInt(COLORS.length);
                codeView.setColor(COLORS[position]);
                break;
            case R.id.btn_change_text_size:
                codeView.setFontSize(FONT_SIZES[random.nextInt(FONT_SIZES.length)]);
                break;
            case R.id.btn_change_num_count:
                codeView.setCodeCount(CODE_COUNT[random.nextInt(CODE_COUNT.length)]);
                break;
            case R.id.btn_change_line_count:
                codeView.setLineCount(LINE_COUNT[random.nextInt(LINE_COUNT.length)]);
                break;
        }
    }
}
