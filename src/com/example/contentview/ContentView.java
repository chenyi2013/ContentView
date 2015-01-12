package com.example.contentview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ContentView extends View {

	private String[] mData = new String[] { "中国人", "我是中国人大中中中", "中呻吟中中",
			"砝码品吕", "中国人", "我是中国人大中中中", "中呻吟中中", "砝码品吕", "中国人", "我是中国人大中中中",
			"中呻吟中中", "砝码品吕", "中国人", "我是中国人大中中中", "中呻吟中中", "砝码品吕" };
	private String mSeparate = "    ";
	private Paint mPaint;
	private int sepatateHeight = 10;
	private int mCurrentLength = 0;
	private int mTotalHeight = 0;
	private int mTextSize = 16;
	private int mTextColor = Color.BLACK;
	private StringBuffer mBuffer = new StringBuffer();
	private ArrayList<Text> texts = new ArrayList<Text>();

	public ContentView(Context context) {
		super(context);
		init();
	}

	public ContentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ContentView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public int getTextSize() {
		return mTextSize;
	}

	public void setTextSize(int mTextSize) {
		this.mTextSize = mTextSize;
		requestLayout();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(mTextColor);
		mPaint.setDither(true);

	}

	/**
	 * 得到字体高度
	 * 
	 * @param fontSize
	 * @return
	 */
	private int getFontHeight(float fontSize, String content) {
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		Rect rect = new Rect();
		paint.getTextBounds(content, 0, content.length(), rect);
		return rect.height();

	}

	/**
	 * 得到字体宽度
	 * 
	 * @return
	 */
	private int getFontWidth(float fontSize, String content) {
		Paint paint = new Paint();
		paint.setTextSize(fontSize);
		return (int) paint.measureText(content);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int index = -1;
		texts.clear();
		mCurrentLength = 0;
		mTotalHeight = 0;
		mBuffer.setLength(0);
		mPaint.setTextSize(mTextSize);
		for (int i = 0; i < mData.length; i++) {

			int len = getFontWidth(mTextSize, mData[i])
					+ getFontWidth(mTextSize, mSeparate) + mCurrentLength;
			if (len > width) {
				int height = getFontHeight(mTextSize, mBuffer.toString());
				mTotalHeight = mTotalHeight + height + sepatateHeight;
				texts.add(new Text(0, mTotalHeight, mBuffer.toString()));
				mCurrentLength = getFontWidth(mTextSize, mData[i])
						+ getFontWidth(mTextSize, mSeparate);
				mBuffer.setLength(0);
				mBuffer.append(mData[i]).append(mSeparate);
				index = i;
			} else {
				mBuffer.append(mData[i]).append(mSeparate);
				if (len == width) {
					int height = getFontHeight(mTextSize, mBuffer.toString());
					mTotalHeight = mTotalHeight + height + sepatateHeight;
					texts.add(new Text(0, mTotalHeight, mBuffer.toString()));
					mCurrentLength = 0;
					mBuffer.setLength(0);
					index = i;
				}
				mCurrentLength = len;
			}

		}
		if (index > 0 && index < mData.length - 1) {

			mBuffer.setLength(0);
			for (int i = index; i < mData.length; i++) {
				mBuffer.append(mData[i]).append(mSeparate);
			}
			int height = getFontHeight(mTextSize, mBuffer.toString());
			mTotalHeight = mTotalHeight + height + sepatateHeight;
			texts.add(new Text(width, mTotalHeight, mBuffer.toString()));

		}
		setMeasuredDimension(width, mTotalHeight + 10);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (Text text : texts) {
			canvas.drawText(text.text, 0, text.height, mPaint);
		}
	}

	class Text {
		int width;
		int height;
		String text;

		public Text(int width, int height, String text) {
			super();
			this.width = width;
			this.height = height;
			this.text = text;
		}

	}
}
