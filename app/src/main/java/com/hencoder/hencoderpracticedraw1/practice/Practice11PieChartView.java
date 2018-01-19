package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Practice11PieChartView extends View {
    private List<Data> mData = Arrays.asList(new Data("Froyo", 30), new Data("Gb", 40),
            new Data("ICS", 50), new Data("JB", 20), new Data("Kitkat", 60));
    private int mInterval = 250;
    private int mCircleRadius = 250;
    private int[] mCenterPoint = new int[]{450, 350};


    private Path mPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private int mTotalNumber;
    private Paint mWhitePaint;
    private List<Integer> mColors = new ArrayList<>();
    private Random mRandom = new Random();
    private RectF mRectF;
    private Paint mSectorPaint;
    private List<Path> mLinePaths = new ArrayList<>();
    private List<float[]> mDataPoints = new ArrayList<float[]>();

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mTotalNumber = getTotalNumber();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setStyle(Paint.Style.FILL);
        mWhitePaint.setColor(Color.WHITE);

        mSectorPaint = new Paint();
        mSectorPaint.setAntiAlias(true);
        mSectorPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mPath.addCircle(mCenterPoint[0], mCenterPoint[1], mCircleRadius, Path.Direction.CCW);
        mPathMeasure = new PathMeasure(mPath, false);

        float dataProgress = 0;
        float[] tan = new float[2];
        for (Data datum : mData) {
            float[] pos = new float[2];
            float centerNumber = dataProgress + (float) datum.getNumber() / 2f;
            float distance = centerNumber * mPathMeasure.getLength() / (float) mTotalNumber;

            mPathMeasure.getPosTan(distance, pos, tan);

            Path linePath = new Path();
            linePath.moveTo(mCenterPoint[0], mCenterPoint[1]);
            linePath.lineTo(pos[0], pos[1]);
            mDataPoints.add(pos);
            Log.d("Practice11PieChartView", "pos:" + Arrays.toString(pos));
            mLinePaths.add(linePath);
            dataProgress += (float) datum.getNumber();

            mColors.add(getRandomColor());
        }
        mRectF = new RectF(mCenterPoint[0] - mCircleRadius, mCenterPoint[1] - mCircleRadius
                , mCenterPoint[0] + mCircleRadius, mCenterPoint[1] + mCircleRadius);

    }

    private void resetRectF() {
        mRectF.set(mCenterPoint[0] - mCircleRadius, mCenterPoint[1] - mCircleRadius
                , mCenterPoint[0] + mCircleRadius, mCenterPoint[1] + mCircleRadius);
    }

    private int getRandomColor() {
        int r = mRandom.nextInt(256);
        int g = mRandom.nextInt(256);
        int b = mRandom.nextInt(256);
        return Color.rgb(r, g, b);
    }

    /**
     * 获取总数量
     *
     * @return
     */
    private int getTotalNumber() {
        int result = 0;
        for (Data datum : mData) {
            result += datum.getNumber();
        }
        return result;
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        float startAngle = 0;
        float sweepAngle;
        for (int i = 0; i < mData.size(); i++) {
            sweepAngle = (float) mData.get(i).getNumber() / (float) mTotalNumber * 360f;

            mSectorPaint.setColor(mColors.get(i));
            canvas.drawArc(mRectF, startAngle, sweepAngle, true, mSectorPaint);

            startAngle += sweepAngle;

            canvas.drawPoint(mDataPoints.get(i)[0], mDataPoints.get(i)[1], mWhitePaint);

            canvas.drawPath(mLinePaths.get(i), mPaint);
        }

        canvas.drawCircle(mCenterPoint[0], mCenterPoint[1], 5, mWhitePaint);
    }
}
