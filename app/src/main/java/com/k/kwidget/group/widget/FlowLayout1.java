package com.k.kwidget.group.widget;




        import android.content.Context;
        import android.util.AttributeSet;
        import android.view.View;
        import android.view.ViewGroup;

        import java.util.ArrayList;
        import java.util.List;

public class FlowLayout1 extends ViewGroup {

    private Line mLine;
    private List<Line> mLineLists;

    private int mHorizontalSpacing = 20;

    public FlowLayout1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public FlowLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generate
        // d constructor stub
    }

    public FlowLayout1(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width_size = MeasureSpec.getSize(widthMeasureSpec)
                - getPaddingLeft() - getPaddingRight();

        int height_size = MeasureSpec.getSize(heightMeasureSpec)
                - getPaddingBottom() - getPaddingTop();

        int width_mode = MeasureSpec.getMode(widthMeasureSpec);

        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        // 使用过的宽度
        int mUserWidth = 0;
        mLine = new Line();

        mLineLists = new ArrayList<Line>();

        int count = getChildCount();

        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width_size,
                    MeasureSpec.AT_MOST);

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height_size,
                    MeasureSpec.AT_MOST);
            child.measure(widthMeasureSpec, heightMeasureSpec);
            if (mLine == null) {
                mLine = new Line();
            }

            // 获取到孩子的测量宽度
            int childMeasuredWidth = child.getMeasuredWidth();
            // 把孩子的测量宽度赋值给使用过的宽度
            mUserWidth += childMeasuredWidth;
            // 就正常显示
            if (mUserWidth < width_size) {
                mLine.addView(child);

            } else {
                // 换行
                mLine = new Line();
                mLineLists.add(mLine);
                mUserWidth = 0;
            }

        }

        int totalHeight = 0;

        for (int i = 0; i < mLineLists.size(); i++) {

            totalHeight += mLineLists.get(i).getHeight();

        }

        int measuredWidth = MeasureSpec.makeMeasureSpec(width_size
                + getPaddingLeft() + getPaddingRight(), MeasureSpec.EXACTLY);

        int measuredHeight = MeasureSpec.makeMeasureSpec(totalHeight
                + getPaddingBottom() + getPaddingTop(), MeasureSpec.EXACTLY);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private class Line {
        List<View> views = new ArrayList<View>();
        int height = 0;
        int lineWidht = 0;

        public void addView(View view) {
            if (height < view.getMeasuredHeight()) {
                height = view.getMeasuredHeight();
            }
            lineWidht += view.getMeasuredWidth();
            views.add(view);
        }

        public int getHeight() {
            // TODO Auto-generated method stub
            return height;
        }

        public void layout(boolean changed, int l, int t, int r, int b) {
            if (!changed) {
                return;
            }

            int left = l;
            int top = t;
            // 剩余宽度
            int freeWidht = r - l - lineWidht;
            // 分割之后的宽度
            int splitSpaceWidth = (int) ((freeWidht / views.size()) + 0.0f);

            for (int i = 0; i < views.size(); i++) {
                View view = views.get(i);
                int widthMeasureSpec = view.getMeasuredWidth()
                        + splitSpaceWidth;
                int heightMeasureSpec = view.getMeasuredHeight();
                view.measure(MeasureSpec.makeMeasureSpec(widthMeasureSpec,
                        MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                        heightMeasureSpec, MeasureSpec.EXACTLY));
            }

            for (int i = 0; i < views.size(); i++) {
                View view = views.get(i);
                int paddingTop = (getHeight() - view.getMeasuredHeight()) / 2;
                view.layout(left, top + paddingTop,
                        left + view.getMeasuredWidth(),
                        top + view.getMeasuredHeight());
                left += view.getMeasuredWidth();
            }

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left = l + getPaddingLeft();
        int top = t + getPaddingTop();
        int right = r - getPaddingRight();

        for (int i = 0; i < mLineLists.size(); i++) {
            Line line = mLineLists.get(i);
            line.layout(changed, left, top, right, top + line.getHeight());
            top += line.getHeight();
        }
    }

}