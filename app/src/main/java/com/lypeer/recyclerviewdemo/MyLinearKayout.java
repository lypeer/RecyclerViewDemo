package com.lypeer.recyclerviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by yang on 2015/8/7.
 */
public class MyLinearKayout extends LinearLayout {
    public MyLinearKayout(Context context) {
        super(context);
    }

    public MyLinearKayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearKayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = widthMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
