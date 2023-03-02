package com.winfo.mypractise.diyview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.winfo.mypractise.R;

import java.text.DecimalFormat;


public class AttendanceProgressBar extends View {
    // 画圆环底部的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private int mProgress = 80;
    //字体颜色
    private int mTextColor;
    // 字体大小
    private float mTextSize;


    public AttendanceProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();

    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.AttendanceProgressBar, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.AttendanceProgressBar_radius, 80);
        mStrokeWidth = typeArray.getDimension(R.styleable.AttendanceProgressBar_strokeWidth, 10);
        mCircleColor = typeArray.getColor(R.styleable.AttendanceProgressBar_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.AttendanceProgressBar_ringColor, 0xFFFFFFFF);
        mTextColor = typeArray.getColor(R.styleable.AttendanceProgressBar_textColor, 0xFF000000);
        mTextSize = typeArray.getDimension(R.styleable.AttendanceProgressBar_textSize, 80);

        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    public AttendanceProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initVariable() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        //        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mStrokeWidth);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        //        mTextPaint.setARGB(255, 255, 255, 255);
        //        mTextPaint.setTextSize(mRadius / 2);
        mTextPaint.setTextSize(mTextSize);
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
    }
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        //        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);
        float f = ((float) mProgress / mTotalProgress);
        canvas.drawCircle(mXCenter, mYCenter, mRingRadius, mCirclePaint);
        RectF oval = new RectF();
        oval.left = (mXCenter - mRingRadius);
        oval.top = (mYCenter - mRingRadius);
        oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
        oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
        canvas.drawArc(oval, -90, f * 360, false, mRingPaint); //
        // canvas.drawCircle(mXCenter, mYCenter, mRadius + mStrokeWidth / 2, mRingPaint);
        String txt =Float.parseFloat(txf(mProgress,mTotalProgress)) *100+ "%";
        mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
        canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4 - dipToPx(30), mTextPaint);
        txt = "出勤率";
        mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
        canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4 - dipToPx(10), mTextPaint);
        txt = mProgress + "/" + mTotalProgress;
        mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
        canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4 + dipToPx(10), mTextPaint);
        txt = "出勤人数";
        mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
        canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4 + dipToPx(30), mTextPaint);
    }
    public static String txf(int a,int b) {
        // TODO 自动生成的方法存根

        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数

        return df.format((float)a/b);

    }
    public void setProgress(int progress,int totalProgress) {
        mProgress = progress;
        mTotalProgress=totalProgress;
        postInvalidate();
    }

    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

}
