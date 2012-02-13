package com.ace.production;

import com.ace.production.services.SmsWatcherService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PayTongActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // start service
        Log.v("PayTongActivity", "on create");
        Intent intent = new Intent(PayTongActivity.this, SmsWatcherService.class);
        startService(intent);
    }
}