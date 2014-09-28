package com.example.myFirstApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	Button go,back,refresh,forward,clearHistory;
	WebView ourBrow ;
	EditText url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		
		ourBrow.setWebViewClient(new ourViewClient());
		try{
		ourBrow.loadUrl("http://www.google.com");
		}catch(Exception e){
			e.printStackTrace();
		}
		go = (Button) findViewById(R.id.bGo);
		back = (Button) findViewById(R.id.bBack);
		refresh = (Button) findViewById(R.id.bRefresh);
		forward = (Button) findViewById(R.id.bForward);
		clearHistory = (Button) findViewById(R.id.bClear);
		url = (EditText) findViewById(R.id.etURL);
		
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bGo:
			String myUrl =  url.getText().toString();
			ourBrow.loadUrl(myUrl);
			break;
		case R.id.bBack:
			if(ourBrow.canGoBack())
				ourBrow.goBack();
			break;
		case R.id.bForward:
			if(ourBrow.canGoForward())
				ourBrow.goForward();
			break;
		case R.id.bRefresh:
			ourBrow.reload();
			break;
		case R.id.bClear:
			ourBrow.clearHistory();
			break;
		  
		}
	}

}
