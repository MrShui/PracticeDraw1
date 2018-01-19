package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice10HistogramView extends View {

    private List<Data> mData = Arrays.asList(new Data("Froyo", 1), new Data("Gb", 10),
            new Data("ICS", 10), new Data("JB", 80), new Data("Kitkat", 120),
            new Data("L", 90), new Data("M", 40), new Data("test", 650));
    private int mPaddingX = 150;
    private int mPaddingY = 80;
    private int mNameHeight = 100;
    private String mName = "直方图";
    private int mNameCoordinateOffset = 130;
    private int mNameTextSize = 40;
    private int mPillarOffset = 20;//柱子之间的间距
    private int mLabelOffset = 10;//标签和坐标轴之间的间距

    private int[] mYEndPoint = new int[]{mPaddingX, mPaddingY};
    private int[] mXEndPoint = new int[2];
    private int[] mOriginalPoint = new int[2];
    private int[] mNamePoint = new int[2];
    private int mViewWidth;
    private int mViewHeight;
    private float mYUnitSpace;//y轴一个单位的长度
    private int mPillarWidth;//柱子的宽度
    private List<RectF> mPillarRects = new ArrayList<>();
    private List<int[]> mLablePoints = new ArrayList<>();

    private Paint mCoordinatePaint;
    private Paint mNamePaint;
    private Paint mPillarPaint;
    private Paint mLabelPaint;

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCoordinatePaint = new Paint();
        mCoordinatePaint.setAntiAlias(true);
        mCoordinatePaint.setColor(Color.WHITE);
        mCoordinatePaint.setStrokeWidth(2);

        mNamePaint = new Paint();
        mNamePaint.setAntiAlias(true);
        mNamePaint.setColor(Color.WHITE);
        mNamePaint.setTextSize(mNameTextSize);

        mPillarPaint = new Paint();
        mPillarPaint.setAntiAlias(true);
        mPillarPaint.setColor(Color.parseColor("#61B013"));

        mLabelPaint = new Paint();
        mLabelPaint.setAntiAlias(true);
        mLabelPaint.setColor(Color.WHITE);
        mLabelPaint.setTextSize(20);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mOriginalPoint[0] = mPaddingX;
        mOriginalPoint[1] = mViewHeight - mPaddingY - mNameHeight;
        mXEndPoint[0] = mViewWidth - mPaddingX;
        mXEndPoint[1] = mViewHeight - mPaddingY - mNameHeight;

        Rect nameBounds = new Rect();
        mNamePaint.getTextBounds(mName, 0, mName.length(), nameBounds);

        mNamePoint[0] = mViewWidth / 2 - nameBounds.width() / 2;
        mNamePoint[1] = mOriginalPoint[1] + mNameCoordinateOffset;
        mYUnitSpace = (float) (mViewHeight - mPaddingY * 2 - mNameHeight) / (float) getMaxY();
        mPillarWidth = (mViewWidth - mPaddingX * 2 - mPillarOffset * (mData.size() + 1)) / mData.size();


        int start;
        int end = mOriginalPoint[0];
        Rect labelBounds = new Rect();
        for (Data datum : mData) {
            //柱形图
            int height = (int) (datum.getNumber() * mYUnitSpace);
            start = end + mPillarOffset;
            end = start + mPillarWidth;
            mPillarRects.add(new RectF(start, mOriginalPoint[1] - height, end, mOriginalPoint[1]));

            //label
            mLabelPaint.getTextBounds(datum.getLable(), 0, datum.getLable().length(), labelBounds);
            int[] label = new int[2];
            label[0] = (start + (end - start) / 2 - labelBounds.width() / 2);
            label[1] = mOriginalPoint[1] + labelBounds.height() + mLabelOffset;
            mLablePoints.add(label);
        }
    }

    /**
     * 获取最大的数值
     *
     * @return
     */
    private int getMaxY() {
        int result = 0;
        for (Data datum : mData) {
            if (datum.getNumber() > result) {
                result = datum.getNumber();
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        drawCoordinate(canvas);
        drawHistogramName(canvas);
        drawPillarAndLabel(canvas);
    }

    private void drawPillarAndLabel(Canvas canvas) {
        for (int i = 0; i < mPillarRects.size(); i++) {
            canvas.drawRect(mPillarRects.get(i), mPillarPaint);
            int[] labelPoint = mLablePoints.get(i);
            canvas.drawText(mData.get(i).getLable(), labelPoint[0], labelPoint[1], mLabelPaint);
        }
    }

    /**
     * 绘制直方图名称
     *
     * @param canvas
     */
    private void drawHistogramName(Canvas canvas) {
        canvas.drawText(mName, mNamePoint[0], mNamePoint[1], mNamePaint);
    }

    /**
     * 绘制坐标系
     *
     * @param canvas
     */
    private void drawCoordinate(Canvas canvas) {
        float[] pts = new float[]{mOriginalPoint[0], mOriginalPoint[1], mYEndPoint[0], mYEndPoint[1],
                mOriginalPoint[0], mOriginalPoint[1], mXEndPoint[0], mXEndPoint[1]};
        canvas.drawLines(pts, mCoordinatePaint);
    }
}
