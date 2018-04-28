package com.k.kwidget.group.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;



public class CornerLayout extends ViewGroup {

    public CornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 定位子组件
     * padding
     * margin
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int paddingLeft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingTop=getPaddingTop();
        int paddingBottom=getPaddingBottom();




        for (int i = 0; i < getChildCount(); i++) {

            View child=getChildAt(i);

            if(i==0){
                //定位到左上角
                child.layout(paddingLeft,paddingTop,child.getMeasuredWidth()+paddingLeft,child.getMeasuredHeight()+paddingTop);
            }
            else if(i==1){
                //定位到右上角
                child.layout(getMeasuredWidth()-child.getMeasuredWidth()-paddingRight,paddingTop,
                        getMeasuredWidth()-paddingRight,child.getMeasuredHeight()+paddingTop);
            }
            else if(i==2){
                //定位到左下角
                child.layout(paddingLeft,getMeasuredHeight()-child.getMeasuredHeight()-paddingBottom,
                        child.getMeasuredWidth()+paddingLeft,getMeasuredHeight()-paddingBottom);
            }else if(i==3){
                //定位到右下角
                child.layout(getMeasuredWidth()-child.getMeasuredWidth()-paddingRight,getMeasuredHeight()-child.getMeasuredHeight()-paddingBottom,
                        getMeasuredWidth()-paddingRight,getMeasuredHeight()-paddingBottom);
            }


        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //先测量所有子组件大小
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //再测量自己的大小
        int width=measureWidth(widthMeasureSpec);
        int height=measureHeight(heightMeasureSpec);

        //应用尺寸
        setMeasuredDimension(width,height);


    }

    /**
     * 测量容器的宽度
     */
    private int measureHeight(int heightMeasureSpec) {

        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        int height=0;

        //match_content或具体指
        if(mode==MeasureSpec.EXACTLY){
            height=size;
        }

        //wrap_content
        else if(mode==MeasureSpec.AT_MOST){
            int aHeight=0;
            int bHeight=0;
            int cHeight=0;
            int dHeight=0;

            for (int i = 0; i <getChildCount(); i++) {
                View child=getChildAt(i);

                if(i==0){
                    aHeight=child.getMeasuredHeight();
                }
                else if(i==1){
                    bHeight=child.getMeasuredHeight();
                }
                else if(i==2){
                    cHeight=child.getMeasuredHeight();
                }
                else if(i==3){
                    dHeight=child.getMeasuredHeight();
                }

            }

            height=Math.max(aHeight,bHeight)+Math.max(cHeight,dHeight)+getPaddingTop()+getPaddingBottom();

        }

        return height;
    }

    /**
     *测量容器的高度
     */
    private int measureWidth(int widthMeasureSpec) {

        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);
        int width=0;

        if(mode==MeasureSpec.EXACTLY){
            width=size;
        }
        else if(mode==MeasureSpec.AT_MOST){

            int aWidth=0;
            int bWidth=0;
            int cWidth=0;
            int dWidth=0;

            for (int i = 0; i <getChildCount() ; i++) {

                View child=getChildAt(i);
                if(i==0){
                    aWidth=child.getMeasuredWidth();
                }
                else if(i==1){
                    bWidth=child.getMeasuredWidth();
                }
                else if(i==2){
                    cWidth=child.getMeasuredWidth();
                }
                else if(i==3){
                    dWidth=child.getMeasuredWidth();
                }

            }
            width=Math.max(aWidth,cWidth)+Math.max(bWidth,dWidth)+getPaddingLeft()+getPaddingRight();

        }

        return width;
    }


}
