package com.k.kwidget.group.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.k.kwidget.R;

/**
 * 设置方位  左上右下
 * 自定义 LayoutParams
 */
public class CornerLayout3 extends ViewGroup {

    public CornerLayout3(Context context, AttributeSet attrs) {
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

            PositionLayoutParams marginLayoutParams= (PositionLayoutParams) child.getLayoutParams();
            int leftMargin=marginLayoutParams.leftMargin;
            int rightMargin=marginLayoutParams.rightMargin;
            int topMargin=marginLayoutParams.topMargin;
            int bottomMargin=marginLayoutParams.bottomMargin;
            int position=marginLayoutParams.position;

            if(i==0&&position==PositionLayoutParams.NONE||position==PositionLayoutParams.LEFT_TOP){
                //定位到左上角
                child.layout(paddingLeft+leftMargin,paddingTop+topMargin,
                        child.getMeasuredWidth()+paddingLeft+leftMargin,child.getMeasuredHeight()+paddingTop+topMargin);
            }
            else if(i==1&&position==PositionLayoutParams.NONE||position==PositionLayoutParams.RIFHT_TOP){
                //定位到右上角
                child.layout(getMeasuredWidth()-child.getMeasuredWidth()-paddingRight-rightMargin,paddingTop+topMargin,
                        getMeasuredWidth()-paddingRight-rightMargin,child.getMeasuredHeight()+paddingTop+topMargin);
            }
            else if(i==2&&position==PositionLayoutParams.NONE||position==PositionLayoutParams.LEFT_BOTTOM){
                //定位到左下角
                child.layout(paddingLeft+leftMargin,getMeasuredHeight()-child.getMeasuredHeight()-paddingBottom-bottomMargin,
                        child.getMeasuredWidth()+paddingLeft+leftMargin,getMeasuredHeight()-paddingBottom-bottomMargin);
            }else if(i==3&&position==PositionLayoutParams.NONE||position==PositionLayoutParams.RIGHT_BOTTOM){
                //定位到右下角
                child.layout(getMeasuredWidth()-child.getMeasuredWidth()-paddingRight-rightMargin,getMeasuredHeight()-child.getMeasuredHeight()-paddingBottom-bottomMargin,
                        getMeasuredWidth()-paddingRight-rightMargin,getMeasuredHeight()-paddingBottom-bottomMargin);
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
            int aMargin=0;
            int bMargin=0;
            int cMargin=0;
            int dMargin=0;

            for (int i = 0; i <getChildCount(); i++) {
                View child=getChildAt(i);
                MarginLayoutParams marginLayoutParams= (MarginLayoutParams) getChildAt(i).getLayoutParams();

                if(i==0){
                    aHeight=child.getMeasuredHeight();
                    aMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }
                else if(i==1){
                    bHeight=child.getMeasuredHeight();
                    bMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }
                else if(i==2){
                    cHeight=child.getMeasuredHeight();
                    cMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }
                else if(i==3){
                    dHeight=child.getMeasuredHeight();
                    dMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }

            }

            height=Math.max(aHeight,bHeight)+Math.max(cHeight,dHeight)+getPaddingTop()+getPaddingBottom()
                   +Math.max(aMargin,bMargin)+Math.max(cMargin,dMargin);

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
            int aMargin=0;
            int bMargin=0;
            int cMargin=0;
            int dMargin=0;

            for (int i = 0; i <getChildCount() ; i++) {

                MarginLayoutParams marginLayoutParams= (MarginLayoutParams) getChildAt(i).getLayoutParams();

                View child=getChildAt(i);
                if(i==0){
                    aWidth=child.getMeasuredWidth();
                    aMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }
                else if(i==1){
                    bWidth=child.getMeasuredWidth();
                    bMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }
                else if(i==2){
                    cWidth=child.getMeasuredWidth();
                    cMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }
                else if(i==3){
                    dWidth=child.getMeasuredWidth();
                    cMargin=marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
                }

            }
            width=Math.max(aWidth,cWidth)+Math.max(bWidth,dWidth)+getPaddingLeft()+getPaddingRight()
                   +Math.max(aMargin,cMargin)+Math.max(bMargin,dMargin);

        }

        return width;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new PositionLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new PositionLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PositionLayoutParams(getContext(),attrs);
    }


    public static  class PositionLayoutParams extends ViewGroup.MarginLayoutParams{

        public static final int LEFT_TOP=0;//左上
        public static final int RIFHT_TOP=1;//右上
        public static final int LEFT_BOTTOM=2;//左下
        public static final int RIGHT_BOTTOM=3;//右下
        public static final int NONE=-1;
        public int position;

        public PositionLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray typedArray=c.obtainStyledAttributes(attrs,R.styleable.CornerLayout3);
            position=typedArray.getInt(R.styleable.CornerLayout3_layout_position,NONE);
            typedArray.recycle();
        }

        public PositionLayoutParams(int width, int height) {
            super(width, height);
        }

        public PositionLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public PositionLayoutParams(LayoutParams source) {
            super(source);
        }
    }



}
