/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.e.library.widget.decorator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e.library.R;
import com.e.library.utils.EDensityUtils;

/**
 * RecyclerView线性分割线
 */
public class EDividerDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Context mContext;
    private Drawable mDivider;

    private int mOrientation;

    private int mLeftPadding = 0;
    private int mRightPadding = 0;

    private int mTopPadding = 0;
    private int mBottomPadding = 0;

    public EDividerDecoration(Context context){
        this(context,VERTICAL_LIST);
    }

    public EDividerDecoration(Context context, int orientation) {
        this.mContext = context;
        setDefaultDivider();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    public EDividerDecoration setHorizontalOrientation(){
        setOrientation(HORIZONTAL_LIST);
        return this;
    }

    public EDividerDecoration setVerticalOrientation(){
        setOrientation(VERTICAL_LIST);
        return this;
    }

    public EDividerDecoration setLeftPadding(int leftPadding) {
        this.mLeftPadding = EDensityUtils.dip2px(mContext,leftPadding);
        return this;
    }

    public EDividerDecoration setRightPadding(int rightPadding) {
        this.mRightPadding = EDensityUtils.dip2px(mContext,rightPadding);
        return this;
    }

    public EDividerDecoration setTopPadding(int topPadding) {
        this.mTopPadding = EDensityUtils.dip2px(mContext,topPadding);
        return this;
    }

    public EDividerDecoration setBottomPadding(int bottomPadding) {
        this.mBottomPadding = EDensityUtils.dip2px(mContext,bottomPadding);
        return this;
    }

    public EDividerDecoration setDivider(int drawableRes){
        mDivider = mContext.getResources().getDrawable(drawableRes);
        return this;
    }

    public EDividerDecoration setDivider(Drawable divider){
        this.mDivider = divider;
        return this;
    }

    public void setDivider(int[] attrs){
        final TypedArray a = mContext.obtainStyledAttributes(attrs);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    public void setDefaultDivider(){
        setDivider(ATTRS);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft()+mLeftPadding;
        final int right = parent.getWidth() - parent.getPaddingRight()-mRightPadding;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop()+mTopPadding;
        final int bottom = parent.getHeight() - parent.getPaddingBottom()-mBottomPadding;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
