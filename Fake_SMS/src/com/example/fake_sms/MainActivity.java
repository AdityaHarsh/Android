package com.example.fake_sms;
import android.provider.ContactsContract.Contacts;  
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity{
Button b1, b2, b3;
EditText msg, name;
String MSG, NAME, number, type;
private static final int CONTACT_PICKER_RESULT = 1001;  
public static final int PICK_CONTACT    = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 msg=(EditText)findViewById(R.id.msg);
		 name=(EditText)findViewById(R.id.name);
		 
	    b1=(Button)findViewById(R.id.button1);
	    b2=(Button)findViewById(R.id.button2);
	    b3=(Button)findViewById(R.id.button3);
	    
		
	    b3.setOnClickListener(new OnClickListener(){

	    	@Override
	    	public void onClick(View arg0) {
	    		// TODO Auto-generated method stub
	    		MSG=msg.getText().toString();
			    //NAME=name.getText().toString();
		        ContentValues values = new ContentValues();
		        values.put("address", number);
		        values.put("body",MSG);
		       // getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
		        getContentResolver().insert(Uri.parse("content://sms/sent"), values);
	    	
	    	}
	    	});
	    
	    
	    b1.setOnClickListener(new OnClickListener(){

	    	@Override
	    	public void onClick(View arg0) {
	    		// TODO Auto-generated method stub
	    		Thread timer=new Thread()
	    		{
	    			public void run()
	    			{
	    				try
	    				{
	    				Intent intent = new Intent(Intent.ACTION_MAIN);
	    				intent.addCategory(Intent.CATEGORY_HOME);
	    				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    				startActivity(intent);
	    				finish();
	    				sleep(200000);
	    				
	    				} 
	    			catch(InterruptedException e)
	    				{
	    				e.printStackTrace();
	    				}
	    			finally
	    				{
	    				MSG=msg.getText().toString();
	    			   // NAME=name.getText().toString();
	    		        ContentValues values = new ContentValues();
	    		        values.put("address", number);
	    		        values.put("body",MSG);
	    		        getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
	    		       // getContentResolver().insert(Uri.parse("content://sms/sent"), values);
	    		        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	    				final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
	    				r.play();
	    		      	}
	    		}
		};
	    		
	    		timer.start();	
	    	}
	    	});
		
	    b2.setOnClickListener(new OnClickListener(){

	    	@Override
	    	public void onClick(View arg0) {
	    		// TODO Auto-generated method stub
	    		
	    		//Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				   //intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
				   //startActivityForResult(intent, 1);
	    		  
	    		   // Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,Contacts.CONTENT_URI);  
	    		    //startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
	    		  
	    		//Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
                  //  startActivityForResult(intent, PICK_CONTACT);
	    		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	            // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
	            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
	            startActivityForResult(intent, 1);  
	    	 
	    	}
	    	});
	}
	    @Override
        public void onActivityResult(int reqCode, int resultCode, Intent data) {
                super.onActivityResult(reqCode, resultCode, data);

               // switch (reqCode) {
                 //       case (PICK_CONTACT):
                               // if (resultCode == Activity.RESULT_OK) {
                try{
                                        Uri contactData = data.getData();
                                       // Uri contactNumber = data.getData();
                                 //       Cursor c = managedQuery(contactData, null, null, null, null);
                                        
                                   //     if (c.moveToFirst()) {
                                     //           String name1 = c.getString(c.getColumnIndexOrThrow(Contacts.DISPLAY_NAME));
                                       //         String name2 = c.getString(c.getColumnIndexOrThrow(Contacts.HAS_PHONE_NUMBER));
                                         //       name.setText(name1);
                                           //     msg.setText(name2);
                                        //}
                                        
                                        Cursor c = getContentResolver().query(contactData, new String[]{ 
                                                ContactsContract.CommonDataKinds.Phone.NUMBER,  
                                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME },
                                            null, null, null);

                                    if (c != null && c.moveToFirst()) {
                                        number = c.getString(0);
                                        type = c.getString(1);
                                        showSelectedNumber(type, number);
                                    } }
                	    			catch(Exception es)
                	    				{
                	    				es.printStackTrace();
                	    				}
                                    
                                }
                   //             break;
                //}
			

	//}
                public void showSelectedNumber(String type, String number) {
                    Toast.makeText(this, type + ": " + number, Toast.LENGTH_LONG).show();   
                    name.setText(type);
                }
}
