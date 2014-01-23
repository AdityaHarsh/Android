package com.example.fakecall;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.content.Intent;
 
public class CallLogUtility {
 
    public  void  AddNumToCallLog(ContentResolver resolver ,String strNum, String str2, int type, long timeInMiliSecond)
        {
            
    	while(strNum.contains("-"))
            {
                strNum =strNum.substring(0,strNum.indexOf('-')) + strNum.substring(strNum.indexOf('-')+1,strNum.length());
            }
            ContentValues values = new ContentValues();
           
            
            values.put(CallLog.Calls.NUMBER, strNum);
            values.put(CallLog.Calls.DATE, timeInMiliSecond);
            values.put(CallLog.Calls.DURATION, "1:00");
            values.put(CallLog.Calls.TYPE, type);
            values.put(CallLog.Calls.NEW, 1);
            values.put(CallLog.Calls.CACHED_NAME, "");
            values.put(CallLog.Calls.CACHED_NUMBER_TYPE, 0);
           // values.put(CallLog.Calls.CACHED_NUMBER_LABEL, "");
            Log.d("AddToCallLog", "Inserting call log placeholder for " + strNum);
 
            if(null != resolver)
            {
                resolver.insert(CallLog.Calls.CONTENT_URI, values);
                
            }
            //getContentResolver().delete(url, where, selectionArgs)
        }
       
}