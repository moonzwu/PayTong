package com.ace.production.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import com.ace.production.text.SMSContent;
import com.ace.production.text.SMSContentParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import java.math.BigDecimal;

public class SMSReceiver extends BroadcastReceiver {
	private String tag = this.getClass().getName();

    public void setActivityHandler(Handler activityHandler) {
        this.activityHandler = activityHandler;
    }

    public Handler activityHandler;

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean isSMS = intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED");
		if (isSMS) {
			Object[] pdus = (Object[])intent.getExtras().get("pdus");
			SmsMessage[] msgs = new SmsMessage[pdus.length];
			for (int i=0; i<pdus.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

				String phoneNumber;
				phoneNumber = msgs[i].getDisplayOriginatingAddress();
				Log.v(tag, phoneNumber);
				
				String messageBody;
				messageBody = msgs[i].getMessageBody();
				Log.v(tag, messageBody);
				
				SMSContentParser parser = new SMSContentParser();
				//SMSContent content = parser.parse(messageBody);
				SMSContent content = new SMSContent();
                content.setBankName("招商银行");
                content.setPayMoney("人民币", new BigDecimal(500.00).setScale(2,BigDecimal.ROUND_HALF_UP));
                content.setPayMoney("美金", new BigDecimal(54.8).setScale(2,BigDecimal.ROUND_HALF_UP));
                if (content != null) {
					Log.v(tag, content.getBankName());
					Log.v(tag, content.getPayMoney("人民币").toString());
                    
                    if (activityHandler != null){
                        Message msg = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(SMSContent.class.getSimpleName(),content);
                        msg.setData(bundle);
                        activityHandler.sendMessage(msg);
                    }
				}
			}
		}
	}
	
}
