package com.ace.production.services;

import com.ace.production.text.SMSContent;
import com.ace.production.text.SMSContentParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
	private String tag = this.getClass().getName();


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
				//messageBody = msgs[i].getMessageBody();
				messageBody = "您尾号8436广发卡12月人民币账单金额647.69，" 
					+ "最低还款100.00，还款到期01月12日。请留意电子账单，" 
					+ "若已还勿理会【广发银行】";
				Log.v(tag, messageBody);
				
				SMSContentParser parser = new SMSContentParser();
				SMSContent content = parser.parse(messageBody);
				Log.v(tag, content.getBankName());
				Log.v(tag, content.getPayMoney("人民币").toString());
			}
		}
	}
	
}
