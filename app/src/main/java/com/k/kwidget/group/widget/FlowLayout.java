package com.k.kwidget.group.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;




public class FlowLayout extends ViewGroup {

    private static final String TAG = "FlowLayout";

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int n=getChildCount();
        int maxViewHeight=0;//当前行的子组件的最大高度
        int maxLineWidth=0;//当前行的的子组件的总宽度
        int totalHeight=0;//累计高度
        int width=getMeasuredWidth();//容器宽度 1080

        for (int i = 0; i <n ; i++) {
            View child=getChildAt(i);

            //判断是否需要换行(已占宽度加子组件宽度是否大于容器宽度)
            if(maxLineWidth+child.getMeasuredWidth()
                    >width-getPaddingLeft()-getPaddingRight()){
                //换行
                //换行后计算已显示行的高度
                totalHeight+=maxViewHeight;
                //新起的一行，已占高度和宽度设置为0
                maxLineWidth=0;
                maxViewHeight=0;
            }

            //摆放子组件,考虑padding
            child.layout(maxLineWidth+getPaddingLeft(),
                    totalHeight+getPaddingTop(),
                    maxLineWidth+child.getMeasuredWidth()+getPaddingLeft(),
                    totalHeight+child.getMeasuredHeight()+getPaddingTop());

            //获取当前行的最高高度
            maxViewHeight=Math.max(maxViewHeight, child.getMeasuredHeight());
            //累加当前行的宽度
            maxLineWidth+= child.getMeasuredWidth();

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(widthMeasureSpec, heightMeasureSpec);


        int width=measureWidth(widthMeasureSpec);
        int height=measureHeight(widthMeasureSpec,heightMeasureSpec);

        setMeasuredDimension(width,height);
    }


    private int measureHeight(int widthMeasureSpec, int heightMeasureSpec) {

        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
       int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=0;

        if(mode==MeasureSpec.EXACTLY){
            height=size;
        }
        else if(mode==MeasureSpec.AT_MOST){
            //wrap_content容器高度跟随内容
//            int width=1080;

            int maxViewHeight=0;//当前行，孩子的最大高度
            int maxLineWidth=0;//当前行孩子总宽度
            int n=getChildCount();

            for (int i = 0; i <n ; i++) {

                View child = getChildAt(i);
                int a =maxLineWidth + child.getMeasuredWidth();
                System.out.println("a======"+a);

                //获取当前行的最高高度
                maxViewHeight = Math.max(maxViewHeight, child.getMeasuredHeight());
                //累加当前行的宽度
                maxLineWidth += child.getMeasuredWidth();
//                System.out.println("maxViewHeight======"+maxViewHeight);

                //预测是否需要换行，第i+1个是否需要换行
                if (i<n-1&&maxLineWidth + getChildAt(i+1).getMeasuredWidth()
                        > width - getPaddingLeft() - getPaddingRight()) {

                    //换行
                    //换行后计算已显示行的高度
                    height += maxViewHeight;
                    //新起的一行，已占高度和宽度设置为0
                    maxLineWidth = 0;
                    maxViewHeight = 0;
                }
                //最后一个孩子
                else if(i==n-1){
                    height += maxViewHeight;
                }
            }

        }

        height+=getPaddingTop()+getPaddingBottom();

        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode=MeasureSpec.getMode(widthMeasureSpec);
        int size=MeasureSpec.getSize(widthMeasureSpec);
        int width=0;
        int childrenWidth=0;//孩子总宽度

        if(mode==MeasureSpec.EXACTLY){
            width=size;
        }
        else if(mode==MeasureSpec.AT_MOST){
            //计算出所有子组件占用的宽度
            for (int i = 0; i <getChildCount() ; i++) {
                View child=getChildAt(i);

                if(child.getMeasuredWidth()>size){
                    throw new IllegalStateException("sub view is too large");
                }

                childrenWidth+=child.getMeasuredWidth();

                //在wrap_content情况下，如果孩子总宽度大于容器的
                //应该取容器最大宽度
                if(childrenWidth>size){
                    width=size;
                }
                else{
                    width=childrenWidth;
                }

                width+=getPaddingLeft()+ getPaddingRight();
            }
        }

        return width;
    }

}
