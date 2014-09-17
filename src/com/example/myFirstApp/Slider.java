package com.example.myFirstApp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener{

	Button handle1,handle2,handle3,handle4;
	SlidingDrawer sd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		handle1 = (Button) findViewById(R.id.handle1);
		handle2 = (Button) findViewById(R.id.handle2);
		handle3 = (Button) findViewById(R.id.handle3);
		handle4 = (Button) findViewById(R.id.handle4);
		CheckBox checkBox = (CheckBox) findViewById(R.id.cb);
		sd = (SlidingDrawer) findViewById(R.id.slidingD);
		sd.setOnDrawerOpenListener(this);
		handle1.setOnClickListener(this);
		handle2.setOnClickListener(this);
		handle3.setOnClickListener(this);
		handle4.setOnClickListener(this);
		checkBox.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) this);
	}

	
	public void onCheckedChanged(CompoundButton group, boolean arg1) {
		// TODO Auto-generated method stub
		if(group.isChecked()){
			sd.lock();
		}else{
			sd.unlock();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.handle1:
				sd.open();
				break;
			case R.id.handle2:
				sd.toggle();
				break;
			case R.id.handle3:
				
				break;
			case R.id.handle4:
				sd.close();
				break;
		}
	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mp = MediaPlayer.create(this, R.raw.radioactive);
		mp.start();
	}

}
