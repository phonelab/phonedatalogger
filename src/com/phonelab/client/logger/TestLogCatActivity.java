package com.phonelab.client.logger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

public class TestLogCatActivity extends Activity {
    /** Called when the activity is first created. */
	private static final String TAG = "LoggerService";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "startActivity");
        TextView tv = (TextView)findViewById(R.id.logTextView);
        tv.setText("Goto this link to view your logs - http://phonelab-logger.nodester.com/" + ((TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
        Intent intent = new Intent(this,OnBootReceiver.class);
        intent.setAction("com.phonelab.client.logger.CUSTOM_INTENT");
        sendBroadcast(intent);
        
//        try {
//        	/*
//        	 * Since Logcat sends to stdout and stderr output to /dev/null
//        	 * In processes that run the Dalvik VM, you can have the system write a copy of the output to the log file. 
//        	 * In this case, the system writes the messages to the log using the log tags stdout and stderr 
//        	 * $ adb shell stop
//			 * $ adb shell setprop log.redirect-stdio true
//			 * $ adb shell start
//			 * 
//			 * Permanent Entry @ /data/local.prop
//        	 */
//	        Process process = Runtime.getRuntime().exec("logcat -d -C");// Verbose filter
//	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//	                         
//	        StringBuilder logString = new StringBuilder();
//	        String line;
//	        
//	        while ((line = bufferedReader.readLine()) != null) {
//	        	logString.append(line);
//	        }
//	        
//	        
//	        
//        } catch (IOException e) {
//        	e.printStackTrace();
//        }
    }
}