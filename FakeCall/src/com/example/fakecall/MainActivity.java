package com.example.fakecall;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
EditText et1, et2;
Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1=(EditText) findViewById(R.id.et1);
		et2=(EditText) findViewById(R.id.et2);
		b1=(Button) findViewById(R.id.b1);
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
					Thread timer=new Thread(){
				public void run(){
				try{
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					finish();
					sleep(10000);
					
				} catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					String s=et1.getText().toString();
					String s2=et2.getText().toString();
					Bundle basket= new Bundle();
					basket.putString("abc", s);
					basket.putString("abc2", s2);
					Intent a=new Intent(MainActivity.this, Incoming.class);
					a.putExtras(basket);
					startActivity(a);
					//Intent o=new Intent("Incoming");
					//startActivity(o);
				}
				}
			};
			timer.start();
				
			
			
			}
			
		});
	}


}
