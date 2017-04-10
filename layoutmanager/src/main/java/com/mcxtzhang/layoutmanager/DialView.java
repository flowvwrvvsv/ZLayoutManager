package com.mcxtzhang.layoutmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class DialView extends View {

    // 宽高
    private int mWidth;
    private int mHeight;

    // 构造方法
    public DialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取屏幕的宽高
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        mWidth = canvas.getWidth() - getPaddingLeft() - getPaddingRight();
        mHeight = canvas.getHeight() - getPaddingTop() - getPaddingBottom();

        super.onDraw(canvas);

        // 画外圆
        Paint paintCircle = new Paint();
        paintCircle.setAntiAlias(true);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paintCircle);

        // 画刻度
        Paint paintDegree = new Paint();
        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            // 区别整点和非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90, paintDegree);
            } else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 30, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60, paintDegree);
            }
            // 通过旋转画布简化坐标运算
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }

        // 画指针
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();


        canvas.drawColor(Color.WHITE);
        paintHour.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, paintHour);

        canvas.saveLayerAlpha(0, 0,400,400,127,LAYER_TYPE_NONE);
        paintHour.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, paintHour);
        canvas.restore();
    }

}