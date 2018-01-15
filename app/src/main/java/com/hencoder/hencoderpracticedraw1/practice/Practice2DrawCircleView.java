package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;

    public Practice2DrawCircleView(Context context) {
        super(context);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(3);
        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setColor(Color.parseColor("#3B7ADA"));

        mPaint4 = new Paint();
        mPaint4.setAntiAlias(true);
        mPaint4.setStyle(Paint.Style.STROKE);
        mPaint4.setStrokeWidth(20 * 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        canvas.drawCircle(330, 150, 150, mPaint1);
        canvas.drawCircle(730, 150, 150, mPaint2);
        canvas.drawCircle(330, 550, 150, mPaint3);
        canvas.drawCircle(730, 550, 150, mPaint4);
    }
}
