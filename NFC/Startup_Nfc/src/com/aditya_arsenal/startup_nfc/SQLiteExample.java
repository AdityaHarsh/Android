package com.aditya_arsenal.startup_nfc;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {
	Button bv, ba, bm, br, bu, bd;
	EditText nfc, number, id, name, cname, des, cn, em, cs, pl,dac  ;
	TextView q;
	String str;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);
       q=(TextView)findViewById(R.id.textView1);
       nfc=(EditText)findViewById(R.id.nfc);
        id=(EditText)findViewById(R.id.edt1);
       number=(EditText)findViewById(R.id.edt2);
      name=(EditText)findViewById(R.id.edt3);
       cname=(EditText)findViewById(R.id.edt4);
       des=(EditText)findViewById(R.id.edt5);
       cn=(EditText)findViewById(R.id.edt6);
       em=(EditText)findViewById(R.id.edt7);
       cs=(EditText)findViewById(R.id.edt8);
       pl=(EditText)findViewById(R.id.edt9);
       dac=(EditText)findViewById(R.id.edt10);
       
        bv=(Button)findViewById(R.id.bv);
        ba=(Button)findViewById(R.id.ba);
        bm=(Button)findViewById(R.id.bm);
        br=(Button)findViewById(R.id.br);
        bu=(Button)findViewById(R.id.bu);
        bd=(Button)findViewById(R.id.bd);
        
        bv.setOnClickListener(this);
        ba.setOnClickListener(this);
        bm.setOnClickListener(this);
        br.setOnClickListener(this);
        bu.setOnClickListener(this);
        bd.setOnClickListener(this);
        Bundle gt=getIntent().getExtras();
 		str=gt.getString("abc");
 		q.setText(str);
		nfc.setText(str);
    }

   
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		case R.id.bv:
			Intent i=new Intent("android.intent.action.SQLVIEW");
			startActivity(i);
			break;
		case R.id.ba:
			
			String sqlid=id.getText().toString();
			String sqlnumber=number.getText().toString();
			String sqlname=name.getText().toString();
			String sqlcname=cname.getText().toString();
			String sqldes=des.getText().toString();
			String sqlcnumber=cn.getText().toString();
			String sqlem=em.getText().toString();
			String sqlcs=cs.getText().toString();
			String sqlpl=pl.getText().toString();
			String sqldac=dac.getText().toString();
			
			HotOrNot entry=new HotOrNot(SQLiteExample.this);
			entry.open();
			
		entry.createEntry(sqlid, sqlnumber, sqlname, sqlcname, sqldes, sqlcnumber, sqlem, sqlcs, sqlpl, sqldac);
			entry.close();
			break;
			
		case R.id.bm:
		String s=nfc.getText().toString();
	
		HotOrNot hon =new HotOrNot(this);
		hon.open();
		String returnedId=hon.getID(s);
		String returnedNumber=hon.getNumber(s);
		String returnedName=hon.getName(s);
		String returnedCName=hon.getCName(s);
		String returnedDes=hon.getDes(s);
		String returnedCNumber=hon.getcn(s);
		String returnedEM=hon.getem(s);
		String returnedCS=hon.getcs(s);
		String returnedPL=hon.getpl(s);
		String returnedDAC=hon.getdac(s);
		
		
		
		id.setText(returnedId);
		number.setText(returnedNumber);
		name.setText(returnedName);
		cname.setText(returnedCName);
		des.setText(returnedDes);
		cn.setText(returnedCNumber);
		em.setText(returnedEM);
		cs.setText(returnedCS);
		pl.setText(returnedPL);
		dac.setText(returnedDAC);
		hon.close();
		break;

		//break;
		case R.id.br:
			nfc.setText(null);
			number.setText(null);
			id.setText(null);
			name.setText(null);
			cname.setText(null);
			des.setText(null);
			cn.setText(null);
			em.setText(null);
			cs.setText(null);
			pl.setText(null);
			dac.setText(null);
			break;
			
		case R.id.bu:
			String s2=nfc.getText().toString();
			String usqlid=id.getText().toString();
			String usqlnumber=number.getText().toString();
			String usqlname=name.getText().toString();
			String usqlcname=cname.getText().toString();
			String usqldes=des.getText().toString();
			String usqlcnumber=cn.getText().toString();
			String usqlem=em.getText().toString();
			String usqlcs=cs.getText().toString();
			String usqlpl=pl.getText().toString();
			String usqldac=dac.getText().toString();
			HotOrNot ex=new HotOrNot(this);
			ex.open();
			ex.updateEntry(usqlid, usqlnumber, usqlname, usqlcname, usqldes, usqlcnumber, usqlem, usqlcs, usqlpl, usqldac);
			ex.close();
			break;
			
		case R.id.bd:
			String s3=nfc.getText().toString();
			HotOrNot ex1 =new HotOrNot(this);
			ex1.open();
			ex1.deleteEntry(s3);
			ex1.close();
		break;
		}
	}
}

