package com.e.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.e.library.R;

import java.lang.ref.WeakReference;

/**
 * 自定义多样式圆角图形控件
 */
public class EImageView extends ImageView {

    public static final int SHAPE_CIRCLE = 1;   //圆
    public static final int SHAPE_ROUND = 2;    //圆角
    public static final int SHAPE_OVAL = 3;     //椭圆

    private static final int DEFAUT_ROUND_RADIUS = 10;//默认圆角大小

    protected Context mContext;
    protected int borderColor;
    protected int borderWidth;
    protected int mShape;
    protected int roundRadius;
    protected int leftTopRadius;
    protected int rightTopRadius;
    protected int rightBottomRadius;
    protected int leftBottomRadius;
    protected boolean onlyDrawBorder;
    private Paint mBitmapPaint;
    private Paint mBorderPaint;
    private Shader mShader;
    private WeakReference<Bitmap> mWeakBitmap;

    public EImageView(Context context) {
        this(context,null);
    }

    public EImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context,attrs);
        initPaint(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null){
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EImageView);
        mShape = a.getInt(R.styleable.EImageView_shape, SHAPE_CIRCLE);
        borderColor = a.getColor(R.styleable.EImageView_border_color, Color.TRANSPARENT);
        borderWidth = a.getDimensionPixelSize(R.styleable.EImageView_border_width, 0);
        roundRadius = a.getDimensionPixelSize(R.styleable.EImageView_round_radius, DEFAUT_ROUND_RADIUS);
        leftTopRadius = a.getDimensionPixelSize(R.styleable.EImageView_left_top_radius, -1);
        if (leftTopRadius == -1){
            leftTopRadius = roundRadius;
        }
        rightTopRadius = a.getDimensionPixelSize(R.styleable.EImageView_right_top_radius, -1);
        if (rightTopRadius == -1){
            rightTopRadius = roundRadius;
        }
        rightBottomRadius = a.getDimensionPixelSize(R.styleable.EImageView_right_bottom_radius, -1);
        if (rightBottomRadius == -1){
            rightBottomRadius = roundRadius;
        }
        leftBottomRadius = a.getDimensionPixelSize(R.styleable.EImageView_left_bottom_radius, -1);
        if (leftBottomRadius == -1){
            leftBottomRadius = roundRadius;
        }
        onlyDrawBorder = a.getBoolean(R.styleable.EImageView_only_draw_border, false);
        a.recycle();
    }

    private void initPaint(Context context) {
        mContext = context;
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (onlyDrawBorder) {
            super.onDraw(canvas);
        } else {
            Bitmap bmp = drawableToBitmap(getDrawable());
            if (bmp != null) {
                float bmpW = bmp.getWidth();
                float bmpH = bmp.getHeight();
                float h = getHeight();
                float w = getWidth();
                drawShader(canvas, bmp, bmpW, bmpH, w, h);
                drawShape(canvas, w, h);
            }
        }
        drawBorder(canvas);
    }

    private void drawShader(Canvas canvas, Bitmap bmp, float bmpW, float bmpH, float w, float h) {
        mShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setScale(w / bmpW, h / bmpH);
        mShader.setLocalMatrix(matrix);
        mBitmapPaint.setColor(Color.TRANSPARENT);
        mBitmapPaint.setShader(mShader);
        canvas.drawPaint(mBitmapPaint);
    }


    private void drawShape(Canvas canvas, float w, float h) {
        RectF rectF = new RectF();
        mBitmapPaint.setColor(Color.WHITE);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setStyle(Paint.Style.FILL);
        switch (mShape) {
            case SHAPE_CIRCLE:
                float min = Math.min(w, h);
                rectF.left = (w - min) / 2 + borderWidth / 2;
                rectF.top = (h - min) / 2 + borderWidth / 2;
                rectF.right = w - (w - min) / 2 - borderWidth / 2;
                rectF.bottom = h - (h - min) / 2 - borderWidth / 2;
                canvas.drawArc(rectF, 0, 360, true, mBitmapPaint);
                break;
            case SHAPE_ROUND:
                Path path = new Path();
                rectF.left = borderWidth / 2;
                rectF.top = borderWidth / 2;
                rectF.right = w - borderWidth / 2;
                rectF.bottom = h - borderWidth / 2;
                float[] rad = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
                path.addRoundRect(rectF, rad, Path.Direction.CW);
                canvas.drawPath(path, mBitmapPaint);
                break;
            case SHAPE_OVAL:
                rectF.left = borderWidth / 2;
                rectF.top = borderWidth / 2;
                rectF.right = w - borderWidth / 2;
                rectF.bottom = h - borderWidth / 2;
                canvas.drawArc(rectF, 0, 360, true, mBitmapPaint);
                break;
        }
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }


    private void drawBorder(Canvas canvas) {
        if (borderWidth == 0 || borderColor == Color.TRANSPARENT) {
            return;
        }
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(borderWidth);
        mBorderPaint.setColor(borderColor);
        switch (mShape) {
            case SHAPE_CIRCLE:
                float radius = (Math.min(getWidth(), getHeight()) - borderWidth) / 2;
                float cx = getWidth() / 2f;
                float cy = getHeight() / 2f;
                canvas.drawCircle(cx, cy, radius, mBorderPaint);
                break;
            case SHAPE_ROUND:
                Path path = new Path();
                RectF rectF = new RectF();
                rectF.left = borderWidth / 2;
                rectF.top = borderWidth / 2;
                rectF.right = getWidth() - borderWidth / 2;
                rectF.bottom = getHeight() - borderWidth / 2;

                float[] rad = {leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius};
                path.addRoundRect(rectF, rad, Path.Direction.CW);
                canvas.drawPath(path, mBorderPaint);
                return;
            case SHAPE_OVAL:
                RectF rectF2 = new RectF();
                rectF2.left = borderWidth / 2;
                rectF2.top = borderWidth / 2;
                rectF2.right = getWidth() - borderWidth / 2;
                rectF2.bottom = getHeight() - borderWidth / 2;
                canvas.drawArc(rectF2, 0, 360, false, mBorderPaint);
                break;
        }
    }

    public void setBorderColor(int color) {
        if (borderColor == color){
            return;
        }
        borderColor = color;
        mBorderPaint.setColor(borderColor);
        invalidate();
    }

    public void setBorderWidth(int width) {
        if (this.borderWidth == width){
            return;
        }
        this.borderWidth = width;
        invalidate();
    }

    public void setOnlyDrawBorder(boolean onlyDrawBorder) {
        this.onlyDrawBorder = onlyDrawBorder;
        invalidate();
    }

    public void setRoundRadius(int radius) {
        if (roundRadius == radius){
            return;
        }
        this.roundRadius = radius;
        invalidate();
    }
}
