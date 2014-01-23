package com.example.fakecall;

import com.example.fakecall.R.layout;

import android.R.drawable;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Answer extends Activity{
private TextView timerValue;
	Button be;
	String tr;
	private long startTime = 0L;
	
	private Handler customHandler = new Handler();
	
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer);
		timerValue = (TextView) findViewById(R.id.timerValue);
		startTime = SystemClock.uptimeMillis();
		customHandler.postDelayed(updateTimerThread, 0);
		 final AudioManager audio_mngr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
	        audio_mngr .setRingerMode(AudioManager.RINGER_MODE_SILENT);
	        
	     
be = (Button) findViewById(R.id.be);
		
		be.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View view) {
				audio_mngr .setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				timeSwapBuff += timeInMilliseconds;
				customHandler.removeCallbacks(updateTimerThread);
				
				Toast.makeText(getApplicationContext(), "Call Ended", Toast.LENGTH_SHORT).show();
				//int pid = android.os.Process.myPid();
				//android.os.Process.killProcess(pid);

				
				//this.finish();
				//Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
				//intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//startActivity(intent2);
				
			//	Intent o=new Intent("End");
				//startActivity(o);
				
				tr=timerValue.getText().toString();
				Bundle baske= new Bundle();
				baske.putString("abcd", tr);
				
				Intent a=new Intent(Answer.this, End.class);
				a.putExtras(baske);
				startActivity(a);
				
							
			
			}
		});
				
			}
			
		
	private Runnable updateTimerThread = new Runnable() {

		public void run() {
			
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			
			updatedTime = timeSwapBuff + timeInMilliseconds;

			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			int milliseconds = (int) (updatedTime % 1000);
			timerValue.setText("" + mins + ":"
					+ String.format("%02d", secs));
			customHandler.postDelayed(this, 0);
		
		}
	
	};
}
