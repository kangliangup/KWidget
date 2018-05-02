package com.k.kwidget.custom.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ExtendLayout extends LinearLayout {

    private TextView tvDelete;


    public ExtendLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        tvDelete=createDeleteButton();
        addView(tvDelete,0);
    }


    /**
     * 创建删除按钮
     */
    private TextView createDeleteButton(){

        TextView textView=new TextView(getContext());
        textView.setText("删除");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.RED);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(30,30,30,30);

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
          LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setClickable(true);

        return textView;
    }

    /**
     * 重写addView最多只能有两个孩子
     */
    public void addView(View child,int height,int width){
        super.addView(child,width,height);
        if(getChildCount()>2){
            throw new IndexOutOfBoundsException("Sub views is too much");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

       measureChildren(widthMeasureSpec,heightMeasureSpec);
       int width=measureWidth(widthMeasureSpec);
       int height=measureHeight(heightMeasureSpec);
       setMeasuredDimension(width,height);

    }

    private int measureWidth(int widthMeasureSpec) {

        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);
        int width=0;
        int deleteWidth=0;

        if(mode==MeasureSpec.EXACTLY){
            width=size;
        }

        else if(mode==MeasureSpec.AT_MOST||mode==MeasureSpec.UNSPECIFIED){

            for (int i = 0; i < getChildCount(); i++) {
                if(i==0){
                    deleteWidth=getChildAt(0).getMeasuredWidth();
                }
                if(i==1){
                    width=getChildAt(0).getMeasuredWidth();
                }
            }

        }

        return width+deleteWidth;
    }


    private int measureHeight(int heightMeasureSpec) {

        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        int height=0;

        if(mode==MeasureSpec.EXACTLY){
            height=size;
        }
        else if(mode==MeasureSpec.AT_MOST||mode==MeasureSpec.UNSPECIFIED){
            for (int i = 0; i <getChildCount() ; i++) {
                if(i==1){
                    height=getChildAt(1).getMeasuredHeight();
                }
            }

        }

        //重新调整删除按钮的高度
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,height);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(0).setLayoutParams(layoutParams);
        }

        return height;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        View view=getChildAt(0);

        int tvDeleteWidth=view.getMeasuredWidth();
        int tvDeleteHeight=view.getMeasuredHeight();

        tvDelete.layout(0,0,tvDeleteWidth,getMeasuredHeight());
        getChildAt(1).layout(tvDeleteWidth,0,getMeasuredWidth(),getMeasuredHeight());
        //隐藏删除按钮
        scrollTo(tvDeleteWidth,0);

    }

    /**
     * 返回删除按钮
     */
    public View getDeleteButton(){

        return getChildAt(0);
    }

}
