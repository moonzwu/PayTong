package com.ace.production;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.TextView;
import com.ace.production.services.SMSReceiver;
import com.ace.production.text.SMSContent;

public class PayTongActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        receiver.setActivityHandler(mHandler);

    }

    SMSReceiver receiver = new SMSReceiver();


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            EditText bank = (EditText) findViewById(R.id.edit_bank);
            EditText repayment = (EditText) findViewById(R.id.edit_repayment);
            SMSContent smsContent = (SMSContent) msg.getData().get("SMSContent");
            bank.setText(smsContent.getBankName());
            repayment.setText(smsContent.getPayMoney("人民币").toString());
        }
    };


}