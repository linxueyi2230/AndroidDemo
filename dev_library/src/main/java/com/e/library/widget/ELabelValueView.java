package com.e.library.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e.library.R;
import com.e.library.callback.EViewFinder;

/**
 * Created by lxy on 2016/7/13.
 */
public class ELabelValueView extends FrameLayout implements EViewFinder {

    private View view;
    private View mDivide;
    private ImageView mIcon;
    private TextView mLabel;
    private TextView mValue;

    private int divideColor;
    private int divideMarginLeft;
    private boolean divideVisible;

    private int iconWidth;
    private int iconHeight;
    private int iconMargin;
    private boolean iconVisibe;
    private int iconDrawable;

    private String labelText;
    private int labelTextColor;
    private int labelTextSize;
    private int labelWidth;
    private int labelMarginLeft;

    private String valueText;
    private int valueTextColor;
    private int valueTextSize;
    private int valueDrawable;
    private int valuePaddingRight;


    public ELabelValueView(Context context) {
        this(context, null);
    }

    public ELabelValueView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ELabelValueView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        Resources res = context.getResources();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ELabelValueView);

        divideVisible = array.getBoolean(R.styleable.ELabelValueView_divide_visible, true);
        if (divideVisible) {
            divideMarginLeft = array.getDimensionPixelOffset(R.styleable.ELabelValueView_divide_margin_left, 0);
            divideColor = array.getColor(R.styleable.ELabelValueView_divide_color, res.getColor(R.color.e_gray));
        }

        iconVisibe = array.getBoolean(R.styleable.ELabelValueView_icon_visible, true);
        if (iconVisibe) {
            iconWidth = array.getDimensionPixelOffset(R.styleable.ELabelValueView_icon_width, 0);
            iconHeight = array.getDimensionPixelOffset(R.styleable.ELabelValueView_icon_height, 0);
            iconMargin = array.getDimensionPixelOffset(R.styleable.ELabelValueView_icon_margin, 0);
            iconDrawable = array.getResourceId(R.styleable.ELabelValueView_icon_drawable, 0);
        }

        labelText = array.getString(R.styleable.ELabelValueView_label_text);
        labelTextColor = array.getColor(R.styleable.ELabelValueView_label_text_color, res.getColor(R.color.e_black));
        labelTextSize = array.getDimensionPixelOffset(R.styleable.ELabelValueView_label_text_size, 0);
        labelMarginLeft = array.getDimensionPixelOffset(R.styleable.ELabelValueView_label_margin_left, 0);
        labelWidth = array.getDimensionPixelOffset(R.styleable.ELabelValueView_label_width, 0);

        valueText = array.getString(R.styleable.ELabelValueView_value_text);
        valueTextColor = array.getColor(R.styleable.ELabelValueView_value_text_color, res.getColor(R.color.e_gray));
        valueTextSize = array.getDimensionPixelSize(R.styleable.ELabelValueView_value_text_size, 0);
        valueDrawable = array.getResourceId(R.styleable.ELabelValueView_value_drawable, R.mipmap.e_arrow);
        valuePaddingRight = array.getDimensionPixelOffset(R.styleable.ELabelValueView_value_padding_right, 0);

        array.recycle();
    }


    private void initView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.e_label_value, null);

        mDivide = getViewById(R.id.v_divide);
        mIcon = getViewById(R.id.iv_icon);
        mLabel = getViewById(R.id.tv_label);
        mValue = getViewById(R.id.tv_value);


        if (divideVisible) {
            mDivide.setVisibility(VISIBLE);
            mDivide.setBackgroundColor(divideColor);
            if (divideMarginLeft != 0) {
                LayoutParams params = (LayoutParams) mDivide.getLayoutParams();
                params.leftMargin = divideMarginLeft;
                mDivide.setLayoutParams(params);
            }
        } else {
            mDivide.setVisibility(GONE);
        }

        if (iconVisibe && iconDrawable != 0) {
            mIcon.setVisibility(VISIBLE);
            mIcon.setImageResource(iconDrawable);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mIcon.getLayoutParams();
            if (iconWidth != 0 && iconHeight != 0) {
                params.width = iconWidth;
                params.height = iconHeight;
            }

            if (iconMargin != 0) {
                params.leftMargin = iconMargin;
                params.rightMargin = iconMargin;
            }
            mIcon.setLayoutParams(params);
        } else {
            mIcon.setVisibility(GONE);
        }

        mLabel.setText(labelText);
        mLabel.setTextColor(labelTextColor);
        if (labelTextSize >0){
            mLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, labelTextSize);
        }

        if (labelMarginLeft > 0) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLabel.getLayoutParams();
            params.leftMargin = labelMarginLeft;
            mLabel.setLayoutParams(params);
        }

        if (labelWidth > 0) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLabel.getLayoutParams();
            params.width = labelWidth;
            mLabel.setLayoutParams(params);
        }

        mValue.setText(valueText);
        mValue.setTextColor(valueTextColor);
        if (valueTextSize >0){
            mValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueTextSize);
        }
        mValue.setPadding(0, 0, valuePaddingRight, 0);
        if (valueDrawable != 0) {
            Drawable drawable = getResources().getDrawable(valueDrawable);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mValue.setCompoundDrawables(null, null, drawable, null);
        }

        int height = (int) context.getResources().getDimension(R.dimen.e_height_45);
        this.addView(view, LayoutParams.MATCH_PARENT, height);
    }

    public void setLabelText(String text) {
        mLabel.setText(text);
    }

    public void setLabelText(int text) {
        mLabel.setText(text);
    }

    public void setValueText(String text) {
        mValue.setText(text);
    }

    public void setValueText(int text) {
        mValue.setText(text);
    }

    public View getDivide() {
        return mDivide;
    }

    public ImageView getIcon() {
        return mIcon;
    }

    public TextView getLabel() {
        return mLabel;
    }

    public TextView getValue() {
        return mValue;
    }

    @Override
    public <V extends View> V getViewById(int viewId) {
        return getViewById(view, viewId);
    }

    @Override
    public <V extends View> V getViewById(View convertView, int viewId) {
        return (V) convertView.findViewById(viewId);
    }
}
