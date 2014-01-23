package com.example.speaksms;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VoiceofText extends Activity implements OnClickListener,OnInitListener {

  
  private Button reg;
  private Button dereg;

  private int MY_DATA_CHECK_CODE = 0;
  public static TextToSpeech textSpeech;
  public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
  private SMSReceiver receiver;

    @Override
  public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);
   reg = (Button) findViewById(R.id.regButton);
   dereg = (Button) findViewById(R.id.unregButton);
   reg.setOnClickListener(this);
   dereg.setOnClickListener(this);
   Intent findIntent = new Intent();
   findIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
   startActivityForResult(findIntent, MY_DATA_CHECK_CODE);

  }


protected void onActivityResult(int requestCode, 
int resultCode, Intent data) {
	if (requestCode == MY_DATA_CHECK_CODE) {
	 if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
			textSpeech = new TextToSpeech(this, this);
	 } else {
		 Intent installIntent = new Intent();
	 installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
	}
	}
 }

 @Override
 public void onClick(View v) {
   switch (v.getId()) {
	case R.id.regButton:
		  registerSMS();
	break;
	case R.id.unregButton:
	  unregisterSMS();
	break;
   }
}

@Override
public void onInit(int status) {
  if (status == TextToSpeech.SUCCESS) {
	Toast.makeText(VoiceofText.this,
		"Loading...",
					Toast.LENGTH_SHORT).show();
  } else 

  if (status == TextToSpeech.ERROR) {
	Toast.makeText(VoiceofText.this,
			"Text To Speech not supported",
					Toast.LENGTH_SHORT).show();
  }
}
@Override
public void onDestroy() {
   if (textSpeech != null) {
	textSpeech.stop();
	textSpeech.shutdown();
   }
 super.onDestroy();
}

public void registerSMS() {
 receiver = new SMSReceiver();
 IntentFilter filter = new IntentFilter();
 filter.addAction(ACTION);
 registerReceiver(receiver, filter);
 Toast.makeText(getApplicationContext(), "registered",
				Toast.LENGTH_SHORT).show();
}

public void unregisterSMS() {
 try {
  unregisterReceiver(receiver);
  Toast.makeText(getApplicationContext(),
					"unregistered",  
					 Toast.LENGTH_SHORT).show();
  } catch (Exception es) {
	  
	Toast.makeText(getApplicationContext(),
					"already unregistered",  
							Toast.LENGTH_SHORT).show();
  }
 }
}
