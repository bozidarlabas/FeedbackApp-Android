package com.bozidar.microdroid.iphoneization.bounceScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.bozidar.microdroid.iphoneization.bounceScrollView.listener.BounceListener;

/**
 * Created by bozidar on 09.09.15..
 */
public class BounceScrollView extends ScrollView implements ViewTreeObserver.OnScrollChangedListener{
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 200;

    private Context mContext;
    private int mMaxYOverscrollDistance;
    private BounceListener listener;

    public BounceScrollView(Context context)
    {
        super(context);
        mContext = context;
        initBounceScrollView();
    }

    public BounceScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        initBounceScrollView();
    }

    public BounceScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        initBounceScrollView();
    }

    private void initBounceScrollView()
    {
        this.getViewTreeObserver().addOnScrollChangedListener(this);
        //get the density of the screen and do some maths with it on the max overscroll distance
        //variable so that you get similar behaviors no matter what the screen size

        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        final float density = metrics.density;

        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent)
    {
        //This is where the magic happens, we have replaced the incoming maxOverScrollY with our own custom variable mMaxYOverscrollDistance;
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverscrollDistance, isTouchEvent);
    }

    public void setOnBounceListener(BounceListener listener){
        this.listener = listener;
    }



    @Override
    public void onScrollChanged() {
        listener.onScroll(this.getScrollY());
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        Log.d("over", scrollY + "");
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY);
    }
}
