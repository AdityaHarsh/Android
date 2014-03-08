package aditya.harsh.smsinboxspeaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
		Thread timer=new Thread(){
			public void run(){
			try{
				sleep(3000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}finally{
				
				Intent o=new Intent("FIRST");
				startActivity(o);
			}
			}
		};
		timer.start();
	}

}

