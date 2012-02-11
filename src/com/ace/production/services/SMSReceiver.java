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
				messageBody = "��β��8436�㷢��12��������˵����647.69��" 
					+ "��ͻ���100.00�������01��12�ա�����������˵���" 
					+ "���ѻ������᡾�㷢���С�";
				Log.v(tag, messageBody);
				
				SMSContentParser parser = new SMSContentParser();
				SMSContent content = parser.parse(messageBody);
				Log.v(tag, content.getBankName());
				Log.v(tag, content.getPayMoney("�����").toString());
			}
		}
	}
	
}