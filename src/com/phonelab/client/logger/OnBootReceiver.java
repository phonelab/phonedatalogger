package com.phonelab.client.logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnBootReceiver extends BroadcastReceiver {
	private static final String TAG = "LoggerService";
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "Boot Completed : On Receive");
		Intent service = new Intent(context, LoggerService.class);
		context.startService(service);
	}

}
