package com.example.fakecall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class End extends Activity implements OnClickListener{
Button bh;
TextView cd;
String g;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end);
		cd=(TextView) findViewById(R.id.cd);
		Bundle gt=getIntent().getExtras();
 		g=gt.getString("abcd");
 		
 		cd.setText(g);
 		Toast.makeText(getApplicationContext(), "Call Duration = "+ g, Toast.LENGTH_SHORT).show();
 		//Toast.makeText(getApplicationContext(), g+ " :Call duration", Toast.LENGTH_LONG).show();
		bh=(Button) findViewById(R.id.bh);
		bh.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		//this.finish();
		Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent2);
		this.finish();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		
		//Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
		//intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//startActivity(intent2);t2);
	}

}
