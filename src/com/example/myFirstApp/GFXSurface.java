package com.example.myFirstApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	MyBringBackSurface ourSurfaceView;
	float x=0, y=0, sX=0, sY=0, fX=0, fY=0;
	Bitmap test, plus;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		setContentView(ourSurfaceView);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			break;
		}
		return true;
	}

	//class inside class
	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		Boolean isRunning = false;

		public MyBringBackSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder(); // check if surface is valid

		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}

		public void resume() {
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				if (!ourHolder.getSurface().isValid()) 
					continue;
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				test = BitmapFactory.decodeResource(getResources(),
						R.drawable.greenball);
				plus = BitmapFactory.decodeResource(getResources(),
						R.drawable.plus);
				if (x != 0 && y != 0) {
					canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
				}
				if (sX != 0 && sY != 0) {
					canvas.drawBitmap(plus, sX-(plus.getWidth()/2), sY-(plus.getHeight()/2), null);
				}
				if (fX != 0 && fY != 0) {
					canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getHeight()/2), null);
				}
				ourHolder.unlockCanvasAndPost(canvas);
			}

		}

	}

}
