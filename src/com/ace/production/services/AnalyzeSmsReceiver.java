package com.ace.production.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AnalyzeSmsReceiver extends BroadcastReceiver   {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("received SMS message");
		System.out.println();
	}

}
