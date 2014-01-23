package com.example.fakecall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.CallLog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Incoming extends Activity{
TextView txt1, txt2, ed;
Button ba, br;
String str, str2, str1;
MediaPlayer ourSong;
Ringtone r;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.incoming);
		txt1=(TextView) findViewById(R.id.txt1);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

		ed=(TextView) findViewById(R.id.ed);
		txt2=(TextView) findViewById(R.id.txt2);
		ba=(Button) findViewById(R.id.ba);
		br=(Button) findViewById(R.id.br);
		Bundle gt=getIntent().getExtras();
	 		str=gt.getString("abc");
	 		str2=gt.getString("abc2");
	 		txt1.setText(str);
	 		txt2.setText(str2);
	 		if ("aditi".equals(str))
	 		{
	 		
	 		//	Resources res = getResources();
	 		//Drawable drawable = res.getDrawable(R.drawable.aditi); 
	 		//rl.setBackgroundDrawable(drawable);
	 		ImageView imgView;
 			imgView = (ImageView)findViewById(R.id.iv);
 			imgView.setVisibility(View.VISIBLE);
 			ed.setVisibility(View.VISIBLE);
 			txt2.setVisibility(View.INVISIBLE);
 			txt1.setVisibility(View.INVISIBLE);
 			
 			
	 		
	 			//View v = this.findViewById(R.id.rl);
	 			//v.setBackgroundColor(0xFFFF0000);
	 		}
	 		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
			r.play();
			 final Vibrator myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
			 myVib.vibrate(50000);
	 		// ourSong = MediaPlayer.create(Incoming.this, R.raw.airtel);
	 		//ourSong.start();
			 
		
			 String stra=txt1.getText().toString();
				
			  long  callTimeInMiliSecond    = System.currentTimeMillis(); //time stamp
			  //CallLogUtility is the class where we have written our add/delete function
			  CallLogUtility utility = new CallLogUtility();
			  //number to add
			// utility.AddNumToCallLog(getBaseContext().getContentResolver(),str, CallLog.Calls.OUTGOING_TYPE, callTimeInMiliSecond);
			 utility.AddNumToCallLog(getBaseContext().getContentResolver(),stra, str2, CallLog.Calls.INCOMING_TYPE, callTimeInMiliSecond);
			// Toast.makeText(getApplicationContext(), "Number added", Toast.LENGTH_SHORT).show();
			 
	 		ba.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//ourSong.stop();
					Intent xy=new Intent("Answer");
					startActivity(xy);
					r.stop();
					myVib.cancel();
										
					}
				
	 			
	 		});
	 		br.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				//	ourSong.stop();
					r.stop();
					myVib.cancel();
					
					Toast.makeText(getApplicationContext(), "Call Ended", Toast.LENGTH_SHORT).show();
				
					System.exit(0);
					}
				
	 			
	 		});
	}
	
	}


