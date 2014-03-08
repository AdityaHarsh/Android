package aditya.harsh.smsinboxspeaker;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {
	private TextToSpeech tts;
	String mes[]=new String[500];
	String temp[]=new String[500];
	int i=0;
	Cursor cursor;
	String y,z;
	ProgressDialog mDialog;
	EditText et1;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main1);
		//setContentView(R.layout.main);
		ListView lViewSMS = (ListView) findViewById(R.id.listViewSMS);
		et1=(EditText)findViewById(R.id.et1);
		tts = new TextToSpeech(this, this);
//Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
        if(fetchInbox()!=null)
        {
        	adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fetchInbox());
        	lViewSMS.setAdapter(adapter);
        	
        }
        lViewSMS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        		         		     
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						//speakOut();
						//Toast.makeText(this, mes[i], Toast.LENGTH_LONG).show();
						//if (et1.getText().toString()!="")
					//	{
							//tts.speak("message is!      "     +mes[cs(position)], TextToSpeech.QUEUE_FLUSH, null);
						//}
						//else
						//{
							//adapter.getItem(arg2)	;				
						tts.speak(adapter.getItem(arg2), TextToSpeech.QUEUE_FLUSH, null);
						//}
					}
        		});
et1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				// When user changed the Text
				MainActivity.this.adapter.getFilter().filter(cs);
				//temp[i]=
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub							
			}
		});
       
    }

    public ArrayList fetchInbox()
    {
    	ArrayList sms = new ArrayList();
    	Uri uriSms = Uri.parse("content://sms/inbox");
    
    	cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);
    	
    	cursor.moveToFirst();
    	mes[0]=cursor.getString(3);
   	 String date1 = cursor.getString(2);
	 Long timestamp1 = Long.parseLong(date1);    
	   Calendar calendar1 = Calendar.getInstance();
	   calendar1.setTimeInMillis(timestamp1);
	   Date finaldate1 = calendar1.getTime();
	   String smsDate1 = finaldate1.toString();
    	 sms.add("From  "+cursor.getString(1)+" / "+getContactName(getApplicationContext(), cursor.getString(cursor.getColumnIndexOrThrow("address")))+"  ..."+"\n "+ cursor.getString(3)+"\n");
    	 
    	 while  (cursor.moveToNext())
    	{
    		   String address = cursor.getString(1);
    		   String date = cursor.getString(2);
    		   String body = cursor.getString(3);
    		   Long timestamp = Long.parseLong(date);    
    		   Calendar calendar = Calendar.getInstance();
    		   calendar.setTimeInMillis(timestamp);
    		   Date finaldate = calendar.getTime();
    		   String smsDate = finaldate.toString();
    		   
    		   i++;
    		   mes[i]=body;
    	
    		   System.out.println("======&gt; Mobile number =&gt; "+address);
    		   System.out.println("=====&gt; SMS Text =&gt; "+body);

    		   sms.add("From:  "+address+" / "+getContactName(getApplicationContext(), cursor.getString(cursor.getColumnIndexOrThrow("address")))+"... "+"\n"+body+"\n");
    		  
    		 // y=cursor.getString(3);
    		   
    	}
    	return sms;
    	

    }
    @Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);
			

			// tts.setPitch(5); // set pitch level

			// tts.setSpeechRate(2); // set speech speed rate

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
			} 
				//speakOut();
			}

		} 

	

/*	private void speakOut() {
		Toast.makeText(this, mes[i], Toast.LENGTH_LONG).show();
		tts.speak("message is    "+mes[i], TextToSpeech.QUEUE_FLUSH, null);
	}*/
	public String getContactName(Context context, String phoneNumber) {
	    ContentResolver cr = context.getContentResolver();
	    Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
	            Uri.encode(phoneNumber));
	    Cursor cursor = cr.query(uri,
	            new String[] { PhoneLookup.DISPLAY_NAME }, null, null, null);
	    if (cursor == null) {
	        return null;
	    }
	    String contactName = null;
	    if (cursor.moveToFirst()) {
	        contactName = cursor.getString(cursor
	                .getColumnIndex(PhoneLookup.DISPLAY_NAME));
	    }
	    if (cursor != null && !cursor.isClosed()) {
	        cursor.close();
	    }
	    return contactName;
	}
    
}



