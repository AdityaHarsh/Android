package com.aditya_arsenal.startup_nfc;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;


public class HotOrNot {
	public static final String KEY_ID="id";
	public static final String KEY_NUMBER="number";
	public static final String KEY_NAME="name";
	public static final String KEY_CNAME="cname";
	public static final String KEY_DES="designation";
	public static final String KEY_CN="contact_number";
	public static final String KEY_EM="email";
	public static final String KEY_CS="company_status";
	public static final String KEY_PL="photo_link";
	public static final String KEY_DAC="description_about_company";
	private static final String DATABASE_NAME="HotOrNotdb10";
	private static final String DATABASE_TABLE="testpeople";
	private static final int DATABASE_VERSION=1;
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
 
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ID + " TEXT, " + 
					KEY_NUMBER + " TEXT, " +
					KEY_NAME + " TEXT, " +
					KEY_CNAME + " TEXT, " +
					KEY_DES + " TEXT, " +
					KEY_CN + " TEXT, " +
					KEY_EM + " TEXT, " +
					KEY_CS + " TEXT, " +
					KEY_PL + " TEXT, " +
					KEY_DAC + " TEXT);"
						);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	public HotOrNot(Context c){
	ourContext=c;
	
}
	public HotOrNot open(){
	ourHelper= new DbHelper(ourContext);
	ourDatabase=ourHelper.getWritableDatabase();
	return this;
	}
	public void close(){
	ourHelper.close();
		}
	public long createEntry(String sqlid, String sqlnumber, String sqlname, String sqlcname, String sqldes, String sqlcn, String sqlem, String sqlcs, String sqlpl, String sqldac) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_ID, sqlid);
		
		cv.put(KEY_NUMBER, sqlnumber);
		cv.put(KEY_NAME, sqlname);
		cv.put(KEY_CNAME, sqlcname);
		cv.put(KEY_DES, sqldes);
		cv.put(KEY_CN, sqlcn);
		cv.put(KEY_EM, sqlem);
		cv.put(KEY_CS, sqlcs);
		cv.put(KEY_PL, sqlpl);
		cv.put(KEY_DAC, sqldac);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	public String getData() {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result="";
		int iId=c.getColumnIndex(KEY_ID);
		int iNumber=c.getColumnIndex(KEY_NUMBER);
		int iName=c.getColumnIndex(KEY_NAME);
		int iCName=c.getColumnIndex(KEY_CNAME);
		int iDes=c.getColumnIndex(KEY_DES);
		int iCN=c.getColumnIndex(KEY_CN);
		int iEM=c.getColumnIndex(KEY_EM);
		int iCS=c.getColumnIndex(KEY_CS);
		int iPL=c.getColumnIndex(KEY_PL);
		int iDAC=c.getColumnIndex(KEY_DAC);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result=result + c.getString(iId) + " " + c.getString(iNumber) + " " + c.getString(iName) + " " + c.getString(iCName) + " " + c.getString(iDes) + " " + c.getString(iCN) + " " + c.getString(iEM) + " " + c.getString(iCS) + " " + c.getString(iPL) + " " + c.getString(iDAC) + "\n" + "\n";
		}
		return result;
				
	}
	public String getID(String s) 
	{
		// TODO Auto-generated method stub
		
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gID=c.getString(0);
		return gID;
		}
		return null;
	}
	public String getNumber(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gNumber=c.getString(1);
		return gNumber;
		}
		return null;
	}
	
		
	
	public String getName(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gName=c.getString(2);
		return gName;
		}
		return null;
	}
	public String getCName(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gCName=c.getString(3);
		return gCName;
		}
		return null;
	}
	public String getDes(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gDes=c.getString(4);
		return gDes;
		}
		return null;
	}
	public String getcn(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gCn=c.getString(5);
		return gCn;
		}
		return null;
	}
	public String getem(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gEm=c.getString(6);
		return gEm;
		}
		return null;
	}
	public String getcs(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gCs=c.getString(7);
		return gCs;
		}
		return null;
	}
	public String getpl(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gPl=c.getString(8);
		return gPl;
		}
		return null;
	}
	public String getdac(String s) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ID, KEY_NUMBER, KEY_NAME, KEY_CNAME, KEY_DES, KEY_CN, KEY_EM, KEY_CS, KEY_PL, KEY_DAC};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "='" + s + "'", null, null, null, null);
		if (c !=null){
		c.moveToFirst();
		String gDaC=c.getString(9);
		return gDaC;
		}
		return null;
	}
	public void updateEntry(String usqlid, String usqlnumber, String usqlname,
			String usqlcname, String usqldes, String usqlcnumber,
			String usqlem, String usqlcs, String usqlpl, String usqldac) {
		// TODO Auto-generated method stub
		ContentValues cvu=new ContentValues();

		cvu.put(KEY_ID, usqlid);
		cvu.put(KEY_NUMBER, usqlnumber);
		cvu.put(KEY_NAME, usqlname);
		cvu.put(KEY_CNAME, usqlcname);
		cvu.put(KEY_DES, usqldes);
		cvu.put(KEY_CN, usqlcnumber);
		cvu.put(KEY_EM, usqlem);
		cvu.put(KEY_CS, usqlcs);
		cvu.put(KEY_PL, usqlpl);
		cvu.put(KEY_DAC, usqldac);
		ourDatabase.update(DATABASE_TABLE, cvu, KEY_ID + "='" + usqlid + "'", null);
	}
	public void deleteEntry(String s3) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "='" + s3 + "'", null);
	}

	
}
