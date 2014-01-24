package com.androidhive.pushnotifications;

import static com.androidhive.pushnotifications.CommonUtilities.SENDER_ID;
import static com.androidhive.pushnotifications.CommonUtilities.SERVER_URL;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;



import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
//import android.os.StrictMode;
import android.util.Log;





public class RegisterActivity extends Activity {
	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
TextView tv;
	String s;
	
	
	// Internet detector
	ConnectionDetector cd;
	
	// UI elements
	EditText txtName;
	EditText txtEmail;
	
	// Register button
	Button btnRegister, bre;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		 //StrictMode.enableDefaults();
		tv=(TextView)findViewById(R.id.tv);
		
		  getData();
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(RegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(RegisterActivity.this, "Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			// stop executing code by return
			 return;
		}
		
		txtName = (EditText) findViewById(R.id.txtName);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		bre = (Button) findViewById(R.id.bre);
		
		/*
		 * Click event on Register button
		 * */
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Read EditText dat
				String name = txtName.getText().toString();
				String email = txtEmail.getText().toString();
				
				// Check if user filled the form
			//	if(name.trim().length() > 0 && email.trim().length() > 0){
					// Launch Main Activity
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					
					// Registering user on our server					
					// Sending registraiton details to MainActivity
				//	i.putExtra("name", name);
					//i.putExtra("email", email);
					startActivity(i);
					//finish();
				//}else{
					// user doen't filled that data
					// ask him to fill the form
					//alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "Please enter your details", false);
				//}
			}
		});
		
bre.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg1) {
				Intent j = new Intent("post");
				startActivity(j);
			}
});
		
		
	}
	public void getData() {
		
		String result= "";
		InputStream isr= null;
		try{
		HttpClient httpclient=new DefaultHttpClient();
		HttpPost httppost= new HttpPost("http://192.168.1.2/admin1.php");
		//HttpPost httppost= new HttpPost("http://localhost/db.php");
		HttpResponse response=httpclient.execute(httppost);
		HttpEntity entity= response.getEntity();
		isr=entity.getContent();
		
	}
		 catch(Exception e){
	         Log.e("log_tag", "Error in http connection "+e.toString());
	         tv.setText("Couldnt connect to database");
	 }
	 //convert response to string
	 try{
	         BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
	         StringBuilder sb = new StringBuilder();
	         String line = null;
	         while ((line = reader.readLine()) != null) {
	                 sb.append(line + "\n");
	         }
	         isr.close();
	  
	         result=sb.toString();
	 }
	 catch(Exception e){
	         Log.e("log_tag", "Error  converting result "+e.toString());
	 }
	  
	 //parse json data
	try {
		   String s = "";
		   JSONArray jArray = new JSONArray(result);
		   
		   for(int i=0; i<jArray.length();i++){
			   JSONObject json = jArray.getJSONObject(i);
			   s = s + 
					   "No : "+json.getString("No") +"\n"+				  
					   "EVENT NAME : "+json.getString("Event_Name") +"\n"+
					   "DATE : "+json.getString("Date") +"\n"+
					   "TIME : "+json.getString("Time") +"\n"+
					   "DESCRIPTION : "+json.getString("Description") +"\n"+
					   "OPEN OR REGISTRATION : "+json.getString("O_R")+"\n\n";
		   }
		   
		   tv.setText(s);
		
	} catch (Exception e) {
		// TODO: handle exception
		   Log.e("log_tag", "Error Parsing Data "+e.toString());
	}
	 
	 }
}
