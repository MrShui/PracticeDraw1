package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private RectF mRectF;

    public Practice8DrawArcView(Context context) {
        super(context);
        init();
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setAntiAlias(true);

        mPaint2 = new Paint();
        mPaint1.setAntiAlias(true);


        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);

        mRectF = new RectF(350, 300, 650, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        canvas.drawArc(mRectF, 170, 70, false, mPaint1);
        canvas.drawArc(mRectF, 250, 100, true, mPaint2);
        canvas.drawArc(mRectF, 30, 120, false, mPaint3);
    }
}
