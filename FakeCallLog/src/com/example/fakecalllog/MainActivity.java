package com.example.fakecalllog;

import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	 RadioGroup rg;
	   RadioButton dc, mc, rc;
	   Button b1,b2;
	  EditText et1;
	  String str;
	  long  callTimeInMiliSecond    = System.currentTimeMillis(); //time stamp
	  	  
	  CallLogUtility utility = new CallLogUtility();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rg = (RadioGroup) findViewById(R.id.rg);
		dc = (RadioButton) findViewById(R.id.DC);
		dc.setOnClickListener(this);
		et1=(EditText)findViewById(R.id.et);
		rc = (RadioButton) findViewById(R.id.RC);
		rc.setOnClickListener(this);
		mc = (RadioButton) findViewById(R.id.MC);
		mc.setOnClickListener(this);
		b1 = (Button) findViewById(R.id.b1);
		b1.setOnClickListener(this);
		b2 = (Button) findViewById(R.id.b2);
		b2.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.b1:
			str=et1.getText().toString();
			rg.setVisibility(View.VISIBLE);
			break;
		case R.id.b2:
			et1.setText("");
			rg.setVisibility(View.INVISIBLE);
			break;
		case R.id.DC:
			 utility.AddNumToCallLog(getBaseContext().getContentResolver(),str, CallLog.Calls.OUTGOING_TYPE, callTimeInMiliSecond);
			 Toast.makeText(getApplicationContext(), str+" added to Dialled Calls", Toast.LENGTH_SHORT).show();		
			break;
		case R.id.RC:		
			utility.AddNumToCallLog(getBaseContext().getContentResolver(),str, CallLog.Calls.INCOMING_TYPE, callTimeInMiliSecond);
			 Toast.makeText(getApplicationContext(), str+" added to Received Calls", Toast.LENGTH_SHORT).show();
			 break;
		case R.id.MC:		
			utility.AddNumToCallLog(getBaseContext().getContentResolver(),str, CallLog.Calls.MISSED_TYPE, callTimeInMiliSecond);
			 Toast.makeText(getApplicationContext(), str+" added to Missed Calls", Toast.LENGTH_SHORT).show();
			 break;
		}	
	}
}
