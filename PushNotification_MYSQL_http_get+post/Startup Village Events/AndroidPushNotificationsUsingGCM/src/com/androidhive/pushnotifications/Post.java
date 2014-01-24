package com.androidhive.pushnotifications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Post extends Activity{
	 TextView content, te;
     EditText fname, email, login, pass;
     String Name, Email, Login, Pass; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		 content    =   (TextView)findViewById( R.id.content );
		 te    =   (TextView)findViewById( R.id.te );
	        fname      =   (EditText)findViewById(R.id.et1);
	        email      =   (EditText)findViewById(R.id.et2);
	        login      =    (EditText)findViewById(R.id.et3);
	        pass       =   (EditText)findViewById(R.id.et4);
	        Button saveme=(Button)findViewById(R.id.b1);
	        saveme.setOnClickListener(new Button.OnClickListener(){
	        	 
	            public void onClick(View v)
	            {
	                try{
	                     
	                         // CALL GetText method to make post method call
	                        GetText();
	                 }
	                catch(Exception ex)
	                 {
	                    content.setText(" url exeption! " );
	                 }
	            }
	        });  
	    }
	    public  void  GetText()  throws  UnsupportedEncodingException
	    {
	        // Get user defined values
	        Name = fname.getText().toString();
	        Email   = email.getText().toString();
	        Login   = login.getText().toString();
	        Pass   = pass.getText().toString();
	         
	         // Create data variable for sent values to server  
	         
	          String data = URLEncoder.encode("name", "UTF-8") 
	                       + "=" + URLEncoder.encode(Name, "UTF-8"); 

	          data += "&" + URLEncoder.encode("email", "UTF-8") + "="
	                      + URLEncoder.encode(Email, "UTF-8"); 

	          data += "&" + URLEncoder.encode("user", "UTF-8") 
	                      + "=" + URLEncoder.encode(Login, "UTF-8");

	          data += "&" + URLEncoder.encode("pass", "UTF-8") 
	                      + "=" + URLEncoder.encode(Pass, "UTF-8");
	          
	          String text = "";
	          BufferedReader reader=null;

	          // Send data
	        try
	        { 
	          
	            // Defined URL  where to send data
	            URL url = new URL("http://192.168.1.2/post.php");
	        	 //URL url = new URL("http://192.168.5.151/post.php");
	         // Send POST data request

	          URLConnection conn = url.openConnection(); 
	          conn.setDoOutput(true); 
	          OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
	          wr.write( data ); 
	          wr.flush(); 
	      
	          // Get the server response 
	           
	        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        
	        // Read Server Response
	        while((line = reader.readLine()) != null)
	            {
	                   // Append server response in string
	                   sb.append(line + "\n");
	            }
	            
	            
	            text = sb.toString();
	            te.setText("Successfully added");
	        }
	        catch(Exception ex)
	        {
	        	te.setText("Oops! some error occured"); 
	        }
	        finally
	        {
	            try
	            {
	 
	                reader.close();
	            }

	            catch(Exception ex) {}
	        }
	              
	        // Show response on activity
	        content.setText( text  );
	        
	    }
	}

