package com.aditya_arsenal.startup_nfc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tv=(TextView)findViewById(R.id.dx);
		HotOrNot info= new HotOrNot(this);
		info.open();
		String data=(info.getData());
		tv.setText(data);
		info.close();
		
	}

}