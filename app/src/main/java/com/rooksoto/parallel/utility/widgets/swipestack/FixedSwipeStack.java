package com.rooksoto.parallel.utility.widgets.swipestack;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import link.fls.swipestack.SwipeStack;

public class FixedSwipeStack extends SwipeStack {
    public FixedSwipeStack (Context context) {
        super(context);
    }

    public FixedSwipeStack (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedSwipeStack (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent (MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent (MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
