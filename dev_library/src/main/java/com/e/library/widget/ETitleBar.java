package com.e.library.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e.library.R;
import com.e.library.callback.EViewFinder;

/**
 * Created by lxy on 2016/6/23.
 */
public class ETitleBar extends FrameLayout implements EViewFinder{

    private View view;

    private TextView tvBackward;
    private ImageView ivBackward;
    private LinearLayout llBackward;

    private TextView tvForward;
    private ImageView ivForward;
    private LinearLayout llForward;

    private TextView tvTitle;
    private LinearLayout llTitle;

    private String titleText;
    private String backwardText;
    private String forwardText;

    private int titleTextColor;
    private int backwardTextColor;
    private int forwardTextColor;
    private int titleBarBackgroundColor;

    private int backwardIcon;
    private int forwardIcon;

    private boolean isForwardVisible;
    private boolean isForwardIvVisible;
    private boolean isBackwardVisible;
    private boolean isBackwardIvVisible;
    private boolean isBackwardDefFinish;

    public ETitleBar(Context context) {
        this(context,null);
    }

    public ETitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ETitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        initView(context);
    }

    private void initAttrs(Context context, AttributeSet attrs){
        if (attrs == null){
            return;
        }
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.ETitleBar);

        titleText = array.getString(R.styleable.ETitleBar_title_text);
        backwardText = array.getString(R.styleable.ETitleBar_backward_text);
        forwardText = array.getString(R.styleable.ETitleBar_forward_text);

        titleTextColor = array.getColor(R.styleable.ETitleBar_title_color, Color.WHITE);
        forwardTextColor = array.getColor(R.styleable.ETitleBar_forward_color,Color.WHITE);
        backwardTextColor = array.getColor(R.styleable.ETitleBar_backward_color,Color.WHITE);
        titleBarBackgroundColor = array.getColor(R.styleable.ETitleBar_title_bar_color,context.getResources().getColor(R.color.e_blue));

        forwardIcon = array.getResourceId(R.styleable.ETitleBar_forward_icon,0);
        backwardIcon = array.getResourceId(R.styleable.ETitleBar_backward_icon,R.mipmap.e_back);

        isForwardVisible = array.getBoolean(R.styleable.ETitleBar_forward_visible,true);
        isForwardIvVisible = array.getBoolean(R.styleable.ETitleBar_forward_iv_visible,false);
        isBackwardVisible = array.getBoolean(R.styleable.ETitleBar_backward_visible,true);
        isBackwardDefFinish = array.getBoolean(R.styleable.ETitleBar_backward_def_finish,true);
        isBackwardIvVisible = array.getBoolean(R.styleable.ETitleBar_backward_iv_visible,true);

        array.recycle();

    }
    private void initView(Context context){
        view = LayoutInflater.from(context).inflate(R.layout.e_title_bar,null);

        tvBackward = getViewById(R.id.tv_backward);
        ivBackward = getViewById(R.id.iv_backward);
        llBackward = getViewById(R.id.ll_backward);

        tvForward = getViewById(R.id.tv_forward);
        ivForward = getViewById(R.id.iv_forward);
        llForward = getViewById(R.id.ll_forward);

        tvTitle = getViewById(R.id.tv_title);
        llTitle = getViewById(R.id.ll_title);

        setTitle(titleText);
        setForwardText(forwardText);
        setBackwardText(backwardText);

        setTitleColor(titleTextColor);
        setForwardTextColor(forwardTextColor);
        setBackwardTextColor(backwardTextColor);
        setTitleBarBackgroundColor(titleBarBackgroundColor);

        if (backwardIcon !=0 && isBackwardIvVisible){
            showBackwardIv();
            setBackwardIcon(backwardIcon);
        }else {
            hideBackwardIv();
        }

        if (forwardIcon !=0){
            showForwardIv();
            setForwardIcon(forwardIcon);
        }else {
            hideForwardIv();
        }

        ivForward.setVisibility(isForwardIvVisible ?VISIBLE:GONE);
        llForward.setVisibility(isForwardVisible ?VISIBLE:GONE);
        llBackward.setVisibility(isBackwardVisible ?VISIBLE:GONE);

        if (isBackwardDefFinish){
            setDefaultFinishListener(context);
        }

        int height = (int) context.getResources().getDimension(R.dimen.e_height_50);
        this.addView(view,LayoutParams.MATCH_PARENT,height);
    }

    private void setDefaultFinishListener(final Context context){
        if (context instanceof Activity){
            llBackward.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                }
            });
        }
    }

    /**
     * ==============================TEXT==============================
     */
    public void setBackwardText(String backwardText){
        tvBackward.setText(backwardText);
    }

    public void setBackwardText(int backwardText){
        tvBackward.setText(backwardText);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setTitle(int title){
        tvTitle.setText(title);
    }

    public void setForwardText(String forwardText){
        tvForward.setText(forwardText);
    }

    public void setForwardText(int forwardText){
        tvForward.setText(forwardText);
    }

    /**
     * ==============================LISTENER==============================
     */
    public void setOnBackwarListener(OnClickListener listener){
        llBackward.setOnClickListener(listener);
    }

    public void setOnForwardListener(OnClickListener listener){
        llForward.setOnClickListener(listener);
    }

    /**
     * ==============================ICON==============================
     */
    public void setBackwardIcon(int resId){
        ivBackward.setImageResource(resId);
    }

    public void setForwardIcon(int resId){
        ivForward.setImageResource(resId);
    }

    /**
     * ==============================VISIVLE==============================
     */
    public void hideBackwardLayout(){
        llBackward.setVisibility(GONE);
    }

    public void showBackwardLayout(){
        llBackward.setVisibility(VISIBLE);
    }

    public void hideForwardLayout(){
        llForward.setVisibility(GONE);
    }

    public void showForwardLayout(){
        llForward.setVisibility(VISIBLE);
    }

    public void hideBackwordTv(){
        tvBackward.setVisibility(GONE);
    }

    public void showBackwordTv(){
        tvBackward.setVisibility(VISIBLE);
    }

    public void hideForwordTv(){
        tvForward.setVisibility(GONE);
    }

    public void showForwordTv(){
        tvForward.setVisibility(VISIBLE);
    }

    public void hideTitleTv(){
        tvTitle.setVisibility(GONE);
    }

    public void showTitleTv(){
        tvTitle.setVisibility(VISIBLE);
    }

    public void hideBackwardIv(){
        ivBackward.setVisibility(GONE);
    }

    public void showBackwardIv(){
        ivBackward.setVisibility(VISIBLE);
    }

    public void hideForwardIv(){
        ivForward.setVisibility(GONE);
    }

    public void showForwardIv(){
        ivForward.setVisibility(VISIBLE);
    }

    /**
     * ==============================COLOR==============================
     */
    public void setTitleColor(int color){
        tvTitle.setTextColor(color);
    }

    public void setBackwardTextColor(int color){
        tvBackward.setTextColor(color);
    }

    public void setForwardTextColor(int color){
        tvForward.setTextColor(color);
    }

    public void setTitleBarBackgroundColor(int color){
        view.setBackgroundColor(color);
    }

    /**
     * ==============================GETTER==============================
     */
    public TextView getBackwardTv() {
        return tvBackward;
    }

    public ImageView getBackwardIv() {
        return ivBackward;
    }

    public LinearLayout getBackwardLayout() {
        return llBackward;
    }

    public TextView getForwardTv() {
        return tvForward;
    }

    public ImageView getForwardIv() {
        return ivForward;
    }

    public LinearLayout getForwardLayout() {
        return llForward;
    }

    public TextView getTitleTv() {
        return tvTitle;
    }

    public LinearLayout getTitleLayout() {
        return llTitle;
    }

    @Override
    public <V extends View> V getViewById(int viewId) {
        return getViewById(view,viewId);
    }

    @Override
    public <V extends View> V getViewById(View convertView, int viewId) {
        return (V) convertView.findViewById(viewId);
    }
}
