package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint mPaint;
    private Path mPath;
    private RectF mArc1;
    private RectF mArc2;

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPath = new Path();
        mArc1 = new RectF(400, 250, 500, 350);
        mArc2 = new RectF(500, 250, 600, 350);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        mPath.arcTo(mArc1, 150, 200, false);
        mPath.arcTo(mArc2, 190, 200, false);
        mPath.lineTo((400 + 600) / 2, 420);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

    }
}
