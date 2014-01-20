package com.example.battery;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.Ringtone;
import android.os.Vibrator;

public class MainActivity extends Activity {
	 private static final int MY_NOTIFICATION_ID = 1;
	   private NotificationManager notificationManager;
	    private Notification myNotification;
	    private final String myBlog = "Battery low!!!"; 
int st;
Button b2, b1;
Ringtone r;
			    private TextView batteryLevel, batteryVoltage, batteryTemperature,
		            batteryTechnology, batteryStatus, batteryHealth;

		    /** Called when the activity is first created. */
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_main);
		       b1=(Button)findViewById(R.id.b1);
		        b2=(Button)findViewById(R.id.b2);
		        batteryLevel = (TextView) findViewById(R.id.batterylevel);
		        batteryVoltage = (TextView) findViewById(R.id.batteryvoltage);
		        batteryTemperature = (TextView) findViewById(R.id.batterytemperature);
		        batteryTechnology = (TextView) findViewById(R.id.batterytechology);
		        batteryStatus = (TextView) findViewById(R.id.batterystatus);
		        batteryHealth = (TextView) findViewById(R.id.batteryhealth);



b2.setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		finish();
		
		
	}
	});

b1.setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		r.stop();
		//b1.setVisibility(View.INVISIBLE);
	}
	});








		        this.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		    }

		    private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {

		        @Override
		        public void onReceive(Context arg0, Intent arg1) {
		            // TODO Auto-generated method stub

		            if (arg1.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
		                batteryLevel.setText("Level: "
		                        + String.valueOf(arg1.getIntExtra("level", 0)) + "%");
		                st=(arg1.getIntExtra("level", 0));
		                if (st<5)
		                {
		                	
		                	Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		        			 r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		        			r.play();
		        			
		        			 notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		        	         myNotification = new Notification(R.drawable.ic_launcher,
		        	                 "Battery low!!!", System.currentTimeMillis());
		        	         Context context = getApplicationContext();
		        	         String notificationTitle = "Battery low!";
		        	         String notificationText = "make sure u connect charger!";
		        	         Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
		        	                 .parse(myBlog));
		        	         PendingIntent pendingIntent = PendingIntent.getActivity(
		        	                 MainActivity.this, 0, myIntent,
		        	                 Intent.FLAG_ACTIVITY_NEW_TASK);
		        	         myNotification.defaults |= Notification.DEFAULT_SOUND;
		        	         myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		        	         myNotification.setLatestEventInfo(context, notificationTitle,
		        	                 notificationText, pendingIntent);
		        	         notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
		        			
		        			
		                	AlertDialog.Builder builder1 = new AlertDialog.Builder(arg0);
		                    builder1.setMessage("Battery low...Make sure u r connected to charger");
		                    builder1.setCancelable(true);
		                    builder1.setPositiveButton("OK",
		                            new DialogInterface.OnClickListener() {
		                        public void onClick(DialogInterface dialog, int id) {
		                            r.stop();
		                            b1.setVisibility(View.VISIBLE);
		                        	dialog.cancel();
		                        }
		                    });
		                 //   builder1.setNegativeButton("No",
		                   //         new DialogInterface.OnClickListener() {
		                     //   public void onClick(DialogInterface dialog, int id) {
		                       //     dialog.cancel();
		                        //}
		                    //});

		                    AlertDialog alert11 = builder1.create();
		                    alert11.show();
		                	
		                	
		        			
		        			  
		        			  //("Connect to Charger");
		                	Toast.makeText(getApplicationContext(), "Battery low... Connect to charger", Toast.LENGTH_LONG).show();
		                }
		                	if(st>=95) 
		                	 {
			                	Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
			        			 r = RingtoneManager.getRingtone(getApplicationContext(), notification);
			        			r.play();
			        			
			        			 notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			        	         myNotification = new Notification(R.drawable.ic_launcher,
			        	                 "Battery full!", System.currentTimeMillis());
			        	         Context context = getApplicationContext();
			        	         String notificationTitle = "Battery full!";
			        	         String notificationText = "U can remove charger!";
			        	         Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
			        	                 .parse(myBlog));
			        	         PendingIntent pendingIntent = PendingIntent.getActivity(
			        	                 MainActivity.this, 0, myIntent,
			        	                 Intent.FLAG_ACTIVITY_NEW_TASK);
			        	         myNotification.defaults |= Notification.DEFAULT_SOUND;
			        	         myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
			        	         myNotification.setLatestEventInfo(context, notificationTitle,
			        	                 notificationText, pendingIntent);
			        	         notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
			        			
			        			
			        			
			                	AlertDialog.Builder builder1 = new AlertDialog.Builder(arg0);
			                    builder1.setMessage("Battery full... U can disconnect charger");
			                    builder1.setCancelable(true);
			                    builder1.setPositiveButton("OK",
			                            new DialogInterface.OnClickListener() {
			                        public void onClick(DialogInterface dialog, int id) {
			                            r.stop();
			                            b1.setVisibility(View.VISIBLE);
			                        	dialog.cancel();
			                        }
			                    });
			                 //   builder1.setNegativeButton("No",
			                   //         new DialogInterface.OnClickListener() {
			                     //   public void onClick(DialogInterface dialog, int id) {
			                       //     dialog.cancel();
			                        //}
			                    //});

			                    AlertDialog alert11 = builder1.create();
			                    alert11.show();
			                	
			                	
			        			
			        			  
			        			  //("Connect to Charger");
			                	Toast.makeText(getApplicationContext(), "Battery full... U can disconnect charger", Toast.LENGTH_LONG).show();
		                	
		                	 }
		                batteryVoltage
		                        .setText("Voltage: "
		                                + String.valueOf((float) arg1.getIntExtra(
		                                        "voltage", 0) / 1000) + "V");
		                batteryTemperature.setText("Temperature: "
		                        + String.valueOf((float) arg1.getIntExtra(
		                                "temperature", 0) / 10) + "c");
		                batteryTechnology.setText("Technology: "
		                        + arg1.getStringExtra("technology"));

		                int status = arg1.getIntExtra("status",
		                        BatteryManager.BATTERY_STATUS_UNKNOWN);
		                String strStatus;
		                if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
		                    strStatus = "Charging";
		                } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
		                    strStatus = "Dis-charging";
		                } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
		                    strStatus = "Not charging";
		                } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
		                    strStatus = "Full";
		                } else {
		                    strStatus = "Unknown";
		                }
		                batteryStatus.setText("Status: " + strStatus);

		                int health = arg1.getIntExtra("health",
		                        BatteryManager.BATTERY_HEALTH_UNKNOWN);
		                String strHealth;
		                if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
		                    strHealth = "Good";
		                } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
		                    strHealth = "Over Heat";
		                } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
		                    strHealth = "Dead";
		                } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
		                    strHealth = "Over Voltage";
		                } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
		                    strHealth = "Unspecified Failure";
		                } else {
		                    strHealth = "Unknown";
		                }
		                batteryHealth.setText("Health: " + strHealth);

		            }
		        }

		    };
	 }
	 