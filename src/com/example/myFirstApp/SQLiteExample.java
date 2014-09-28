package com.example.myFirstApp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener{

	EditText name;
	Button update,view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		
		name = (EditText) findViewById(R.id.etSQLName);
		update = (Button) findViewById(R.id.bSQLUpdate);
		view = (Button) findViewById(R.id.bSQLopenView);
	
		update.setOnClickListener(this);
		view.setOnClickListener(this);

}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSQLUpdate:
			boolean didItWork = true;
			try{
			
			String nm = name.getText().toString();
			SQLsave entry = new SQLsave(SQLiteExample.this);
			entry.open();
			entry.createEntry(nm);
			entry.close();
			}catch(Exception e){
				didItWork = false;
			}finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			
			break;
		case R.id.bSQLopenView:
			Intent i = new Intent("android.example.myFirstApp.SQLView");
			startActivity(i);
			break;
		
		}
	}

}
