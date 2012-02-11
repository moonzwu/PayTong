package com.ace.production.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.ace.production.PayTongActivity;

public class SmsWatcherService extends Service {

	private NotificationManager notifcationMgr;
	private String tag = "SmsWatcherService";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		notifcationMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Log.v(tag, "in onCreate");
		
		String message = "Sms watcher service is running";
		Notification notification = new Notification(com.ace.production.R.drawable.ic_launcher,
				message, System.currentTimeMillis());
		notification.flags = Notification.FLAG_NO_CLEAR;
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, 
				new Intent(this, PayTongActivity.class), 0);
		notification.setLatestEventInfo(this, tag, message, contentIntent);
		notifcationMgr.notify(0, notification);
	}

	@Override
	public void onDestroy() {
		notifcationMgr.cancelAll();
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(tag, "in onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

}
