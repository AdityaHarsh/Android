package com.example.speaksms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
  private Context con;

   @Override
  public void onReceive(Context context, Intent intent) {
	this.con = context;
	if (intent.getAction().equals(VoiceofText.ACTION)) {
		Bundle bundle = intent.getExtras();
	if (bundle != null) {
	 readSMS(bundle);
	}
	 }
  }


  public void readSMS(Bundle bundle) {
	SmsMessage[] msgs = null;

	try {


	 Object[] pdus = (Object[]) bundle.get("pdus");

	 if (pdus != null) {
  
 
	   msgs = new SmsMessage[pdus.length];

	   String body = null;
	   String Pnum = null;

		  
	   for (int k = 0; k < msgs.length; k++) {
		msgs[k] = SmsMessage.createFromPdu((byte[]) pdus[k]);
			   
		body = msgs[k].getMessageBody().trim();
			  
		Pnum = msgs[k].getOriginatingAddress().trim();

		
		speakSMS(body, Pnum);
		  }
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }

  public void speakSMS(final String body, String Pnum) {

	
	if (Pnum != null) {


	String displayName = getContactName(Pnum);

		if (displayName == null) {
	  displayName = Pnum;
	 }

	if (body != null && body.length() > 0) {
		VoiceofText.textSpeech.speak("SMS From " + displayName + ". . . . . " + body, TextToSpeech.QUEUE_ADD, null);
	}
   }
 }

  public String getContactName(String mobileno) {
	 
	Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
							Uri.encode(mobileno));

	Cursor c = con.getContentResolver().query(uri,
		new String[] { PhoneLookup.DISPLAY_NAME }, null, null, null);
		String displayName = null;

	  	if (c.getCount() > 0) {
	 c.moveToFirst();
	 displayName = c.getString(c.getColumnIndex(PhoneLookup.DISPLAY_NAME));
	}
	   return displayName;
  }
}
