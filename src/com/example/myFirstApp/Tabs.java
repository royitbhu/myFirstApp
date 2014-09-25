package com.example.myFirstApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener{
	TabHost th;
	TabSpec specs;
	Button newTab,bStart, bStop;
	long start=0, stop;
	TextView showResults;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		newTab = (Button) findViewById(R.id.bAddTab);

		bStart = (Button) findViewById(R.id.bStartWatch);
		bStop = (Button) findViewById(R.id.bStopWatch);
		showResults = (TextView) findViewById(R.id.tvShowResults);
		
		newTab.setOnClickListener(this);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		
		th = (TabHost)findViewById(R.id.tabhost);
		th.setup();
		
		specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);
		
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAddTab:
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					
					TextView text = new TextView(Tabs.this);
					text.setText("You've created a new Tab");
					return (text);
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);
			
			break;
		case R.id.bStartWatch:
			start = System.currentTimeMillis();			
			break;
			
		case R.id.bStopWatch:
			stop = System.currentTimeMillis();
			if (start !=0){
				long result = stop-start;
				int millis = (int) result%1000;
				int second = (int) (result/1000)%60;
				int minutes = (int) result/(1000*60);
				
				showResults.setText(String.format("%d:%02d:%02d", minutes,second,millis));
			}
			break;
		}
	}

}
