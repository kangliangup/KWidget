package com.k.kwidget.custom.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Scroller;


public class FlingRemovedListView extends ListView {

    private static final String TAG = "FlingRemovedListActivity";
    private VelocityTracker velocityTracker;//速度跟踪器
    private float preX,preY;
    private float firstX,firstY;
    private View willFlingView;//要滑动的列表项view
    private int position=INVALID_POSITION;//要滑动的列表项的view的索引位置
    private int touchSlop;//滑动最小距离，系统默认8
    public static final int SNAP_VELOCITY=600;
    private boolean isSlide;
    private Scroller scroller;
    private boolean isBack;

    public FlingRemovedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop= ViewConfiguration.get(getContext()).getScaledTouchSlop();
        scroller=new Scroller(context);
    }


    @SuppressLint("LongLogTag")
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        int action=ev.getAction();
        int x= (int) ev.getX();
        int y= (int) ev.getY();

        obtainVelocity(ev);

        switch (action){

            case MotionEvent.ACTION_DOWN:

                //屏蔽正在滑动的时候触摸事件
                if(!scroller.isFinished()){
                    return super.dispatchTouchEvent(ev);
                }

                preX=x;
                preY=y;
                firstX=x;
                firstY=y;

                position=pointToPosition(x,y);//数据集合中索引
                if(position!=INVALID_POSITION){
                    int visibleIndex=position-getFirstVisiblePosition();//当前显示列表索引
                    willFlingView=getChildAt(visibleIndex);
                    Log.i(TAG, "getFirstVisiblePosition: "+getFirstVisiblePosition());
                    Log.i(TAG, "position: "+position);
                    Log.i(TAG, "visibleIndex: "+visibleIndex);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                float xVelocity=velocityTracker.getXVelocity();
//                Log.i(TAG, "xVelocity: "+xVelocity);
                //判断滑动方向.向右滑动
                if(/*Math.abs(xVelocity)>SNAP_VELOCITY||*/x>firstX&&
                        Math.abs(x-preX)>touchSlop&& Math.abs(y-preY)<touchSlop){
                    isSlide=true;
                }
                break;

            case MotionEvent.ACTION_UP:
                releaseVelocity();
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @SuppressLint({"ClickableViewAccessibility", "LongLogTag"})
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if(isSlide&&position!=INVALID_POSITION){
            float x=ev.getX();

        switch (ev.getAction()){

            case MotionEvent.ACTION_MOVE:
                float dx=preX-x;
                Log.i(TAG, "dx: "+dx);
                willFlingView.scrollBy((int) dx,0);
                preX=x;
                break;

            case MotionEvent.ACTION_UP:
                int scrollX=willFlingView.getScrollX();
                int listWidth=getMeasuredWidth();

                Log.i(TAG, "scrollX: "+scrollX);
                if(Math.abs(scrollX)>=listWidth/2){
                    //继续滑动
                    int remain=scrollX+listWidth;
                    scroller.startScroll(scrollX,0,-remain,0);
                    isBack=false;
                }else{
                    //回退
                    scroller.startScroll(scrollX,0,-scrollX,0);
                    isBack=true;
                }

                postInvalidate();

//                willFlingView=null;
//                position=INVALID_POSITION;
                releaseVelocity();
                isSlide=false;
                break;
        }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
       if(scroller.computeScrollOffset()){
           willFlingView.scrollTo(scroller.getCurrX(),0);
           postInvalidate();
           if(scroller.isFinished()){
               /*没有回退 则删除列表项*/
           if(!isBack){
                   onRemoveItemListener.itemRemove(position,getAdapter());
                   willFlingView.scrollTo(0,0);
               }


           }
       }
    }


    /**
     * 创建VelocityTracker对象，和MotionEvent事件对象关联
     */
    private void obtainVelocity(MotionEvent ev) {

        if(velocityTracker==null){
            velocityTracker=VelocityTracker.obtain();
        }
        velocityTracker.addMovement(ev);
        velocityTracker.computeCurrentVelocity(1000);
    }

    /**
     * 释放VelocityTracker对象
     */
    private void releaseVelocity(){
        if(velocityTracker!=null){
            velocityTracker.clear();
            velocityTracker.recycle();
            velocityTracker=null;
        }
    }



    private OnRemoveItemListener onRemoveItemListener;

    public void setOnRemoveItemListener(OnRemoveItemListener onRemoveItemListener) {
        this.onRemoveItemListener = onRemoveItemListener;
    }
    /**
     * 删除item回掉接口
     */
    public interface OnRemoveItemListener{
        void itemRemove(int position, ListAdapter adapter);
    }


}
