package com.ace.production;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.TextView;
import com.ace.production.services.SMSReceiver;
import com.ace.production.text.SMSContent;

import java.util.Set;

public class PayTongActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        receiver.setActivityHandler(mHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    SMSReceiver receiver = new SMSReceiver();


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            EditText bank = (EditText) findViewById(R.id.edit_bank);
            EditText repayment = (EditText) findViewById(R.id.edit_repayment);
            SMSContent smsContent = (SMSContent) msg.getData().get(SMSContent.class.getSimpleName());
            bank.setText(smsContent.getBankName());

            Set<String> currenciesSet = smsContent.getCurrencies();
            String repaymentText = "";
            for (String currency : currenciesSet) {
                repaymentText += currency + ":" + smsContent.getPayMoney(currency).toString() + ",";
            }
            repayment.setText(repaymentText.substring(0, repaymentText.length()-1));
        }
    };


}