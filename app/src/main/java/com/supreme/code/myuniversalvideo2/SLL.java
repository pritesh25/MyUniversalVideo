package com.supreme.code.myuniversalvideo2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SLL extends LinearLayout {
    public SLL(Context context) {
        super(context);
    }

    public SLL(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SLL(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
