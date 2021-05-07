/*
 * Copyright (C) 2021 TD.Miracle(584290367@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


 /**
  * 创建日期：2021/5/7 9:21
  * @author TD.Miracle
  * @version 1.0
  * 文件名称： TimerCircle.java
  * 类说明：圆环计时器
  */
package com.tdmiracle.learnvoc.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.tdmiracle.learnvoc.R;

public class TimerCircle extends View {
    //圆环画笔
    private Paint mPaint;
    //圆画笔
    private Paint nPaint;

    //1.圆的颜色
    private int circle_color;

    //2.圆环颜色
    private int ring_color;

    //3.圆环大小对应着画笔的宽度
    private int ring_width;

    //4.指定控件宽度和长度
    private int width;
    private int height;

    //50.字体颜色
    private int text_color;
    //51.字体大小
    private int text_size;
    //52.路径颜色
    private int path_color;
    //5.通过宽度和高度计算得出圆的半径
    private int radius;

    //6.指定动画的当前值，比如指定动画从0-10000。
    private int current_value;

    //7.进行到x秒时，对应的圆弧弧度为 x/ * 360.f x可以currentValue得出 currentValue/1000代表秒
    private float  angle_value;//圆弧角度

    //8.通过valueAnimator我们可以获得currentValue
    private ValueAnimator animator;

    //9.表示valueAnimator的持续时间，这里设置为和最大倒计时时间相同
    private float duration;

    //10.最大倒计时时间，如果1分钟计时，这里就有600000ms，单位是ms
    private int maxTime=10000;
    //这里设置为10是为了避免  angleValue = currentValue/maxTime*360.0f除数为0的异常，如果既没有在xml中设置最大值就会报错，这是由绘制流程决定的。

    //11.当前的时间是指倒计时剩余时间，需要显示在圆中
    private int currentTime;

    private onFinishListener finishListenter;

    public TimerCircle(Context context) {
        this(context,null);//12.TimerCircle circle = new TimerCircle()时调用该构造方法
    }



    public TimerCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);//13.通过xml使用自定义View时使用该构造方法
    }

    public TimerCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);//14.一般不会主动调用该方法，除非手动指定调用。

        //15.获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimerCircle);
        circle_color = typedArray.getColor(R.styleable.TimerCircle_circleColor, Color.BLUE);
        ring_color = typedArray.getInteger(R.styleable.TimerCircle_ringColor, 0);
        ring_width = (int) typedArray.getDimension(R.styleable.TimerCircle_width, getResources().getDimension(R.dimen.width));
        text_color = typedArray.getColor(R.styleable.TimerCircle_path, Color.BLUE);
        text_size = (int) typedArray.getDimension(R.styleable.TimerCircle_textSize,getResources().getDimension(R.dimen.word_small));
        path_color = typedArray.getColor(R.styleable.TimerCircle_path, Color.BLUE);
        typedArray.recycle();
        InitPaint();

    }

    private void InitPaint() {
        mPaint = new Paint();
        mPaint.setColor(ring_color);
        mPaint.setAntiAlias(true);
        nPaint = new Paint();
        nPaint.setAntiAlias(true);
        nPaint.setColor(circle_color);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //16.如果padding和wrap-content失效，还要在这里处理
        int widthPixels = this.getResources().getDisplayMetrics().widthPixels;//17. 获取屏幕宽度
        int heightPixels = this.getResources().getDisplayMetrics().heightPixels;//18.获取屏幕高度

        //19.测量，目的是为了根据指定的宽高和屏幕的宽高最终确定圆的半径，四个中最小的即为半径
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int minWidth = Math.min(width, widthPixels);
        int minHeight = Math.min(height, heightPixels);

        setMeasuredDimension(Math.min(minHeight, minWidth), Math.min(minHeight, minWidth));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //20.确定内圆的半径
        int in_radius = (this.getWidth()-2*ring_width)/2;

        //21.确定圆的圆心
        int center = this.getWidth()-in_radius-ring_width;

        //22.绘制内圆和圆环
        drawInner(canvas, in_radius,center);

        //23.绘制倒计时的字体
        drawText(canvas,center);

    }



    private void drawInner(Canvas canvas, int radius, int center) {
        //24.先画出内圆
        canvas.drawCircle(center, center, radius, nPaint);

        //25.画圆环，设置为空心圆，指定半径为内圆的半径，画笔的宽度就是圆环的宽度
        mPaint.setStyle(Paint.Style.STROKE);//26.设置空心圆
        mPaint.setStrokeWidth(ring_width);//27.画笔宽度即圆环宽度
        mPaint.setColor(ring_color);//28. 圆环的颜色
        canvas.drawCircle(center, center, radius, mPaint);


        //30.内圆的外接矩形，有什么作用？绘制圆弧时根据外接矩形绘制
        RectF rectF = new RectF(center-radius,center-radius,center+radius,center+radius);

        //31.计算弧度，通过当前的currentValue的值得到
        angle_value = current_value * 360.0f / maxTime * 1.0f;

        //32.设置阴影大小和颜色
        mPaint.setShadowLayer(10, 10, 10, Color.BLUE);

        //33.指定线帽样式，可以理解为一条有宽度的直线的两端是带有弧度的
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //34.圆弧的颜色
        mPaint.setColor(path_color);

        //35.绘制圆弧
        canvas.drawArc(rectF, -90,angle_value , false, mPaint);
    }

    public void setDuration(int duration, final int maxTime) {
        //36.如果外部指定了最大倒计时的时间，则xml定义的最大倒计时无效，以外部设置的为准
        this.maxTime=maxTime;
        //37.持续时间和最大时间保持一致，方便计算
        this.duration=duration;
        if (animator != null) {
            animator.cancel();
        } else {
            animator = ValueAnimator.ofInt(0, maxTime);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //38.获取此时的进度
                    current_value = (int) animation.getAnimatedValue();
                    if(current_value==maxTime){
                        finishListenter.onFinish();
                    }
                    //39.invalidate()方法系统会自动调用 View的onDraw()方法。
                    invalidate();
                }
            });
            //40.线性插值器，匀速变化
            animator.setInterpolator(new LinearInterpolator());
        }
        animator.setDuration(duration);
        animator.start();
    }

    private void drawText(Canvas canvas,int center) {
        //41.计算当前的剩余的时间,单位s
        currentTime=(maxTime-current_value)/1000;

        //42.显示的倒计时字符串
        String Text=null;

        if(currentTime<10){
            Text="00:0"+currentTime;
        }else if(currentTime>=10&&currentTime<=60){
            Text="00:"+currentTime;
        }else if(currentTime>60&&currentTime<600){
            int min = currentTime/60;
            int sen = currentTime%60;
            if (sen<10) {
                Text="0"+min+":0"+sen;
            }else{
                Text="0"+min+":"+sen;
            }

        }else{
            int min = currentTime/60;
            int sen = currentTime%60;
            if (sen<10) {
                Text=min+":0"+sen;
            }else{
                Text=min+":"+sen;
            }
        }

        // 43.设置文字居中，以左下角为基准的（x，y）就是这里的center baseline。具体的关于drawText需要查看https://blog.csdn.net/harvic880925/article/details/50423762/
        mPaint.setTextAlign(Paint.Align.CENTER);
        // 44.设置文字颜色
        mPaint.setColor(text_color);
        mPaint.setTextSize(text_size);
        mPaint.setStrokeWidth(0);//清除画笔宽度
        mPaint.clearShadowLayer();//清除阴影
        // 45.文字边框
        Rect bounds = new Rect();
        // 46.获得绘制文字的边界矩形
        mPaint.getTextBounds(Text, 0, Text.length(), bounds);
        // 47.获取绘制Text时的四条线
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        // 48.计算文字的基线
        int baseline = center - (fontMetrics.bottom+fontMetrics.top)/2;
        // 49.绘制表示进度的文字
        canvas.drawText(Text, center, baseline, mPaint);

    }

    public interface onFinishListener{
        void onFinish();
    }

    public void setFinishListenter(onFinishListener listenter){
        this.finishListenter = listenter;
    }
}
