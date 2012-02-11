package com.ace.production.text;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SMSContentParser {

	public SMSContent parse(String data) {
		SMSContent content = null;
		if (data.contains("�㷢����")) {
			content = generateGdbBankSMSContent(data);
		}
		if (data.contains("��������")) {
			content = generateCmbBankSMSContent(data);
		}
		
		return content;
	}

	private SMSContent generateGdbBankSMSContent(String data) {
		SMSContent content = new SMSContent();
		Pattern p = Pattern.compile("��β��(\\d{4})�㷢��(\\d{2})��" +
				"(�����)�˵����(-?\\d+\\.\\d+)?��" +
				"��ͻ���(-?\\d+\\.\\d+)?��" +
				"�����(\\d{2})��(\\d{2})�ա�" +
				"����������˵������ѻ�����᡾(\\W{4})��");
		Matcher m = p.matcher(data);
		if(m.matches()) {
			int count = m.groupCount();
			content.setCardNumberTrail(m.group(1));
			content.setBillingMonth(Integer.parseInt(m.group(2)));
			content.setPayMoney(m.group(3), new BigDecimal(m.group(4)));
			content.setTheLeastPayMoney(Float.parseFloat(m.group(5)));
			Date payTime = new Date();
			payTime.setMonth(Integer.parseInt(m.group(6)));
			payTime.setDate(Integer.parseInt(m.group(7)));
			content.setPayTime(payTime);
			content.setBankName(m.group(count));
		}
		
		return content;
	}
	
	
	private SMSContent generateCmbBankSMSContent(String data) {
		SMSContent content = new SMSContent();
		Pattern p = Pattern.compile("(\\W+)�����������и������ÿ�(\\d{2})��" +
				"�˵����(�����)(-?\\d+\\,\\d+\\.\\d+)?��(����)(-?\\d+\\.\\d+)?��" +
				"���ڻ�����(\\d{2})��(\\d{2})��\\[(\\W{4})\\]");
		Matcher m = p.matcher(data);
		if(m.find()) {
			int count = m.groupCount();
			content.setBillingMonth(Integer.parseInt(m.group(2)));
			content.setPayMoney(m.group(3), new BigDecimal(m.group(4).replace(",", "")));
			content.setPayMoney(m.group(5), new BigDecimal(m.group(6).replaceAll(",", "")));
			Date payTime = new Date();
			payTime.setMonth(Integer.parseInt(m.group(7)));
			payTime.setDate(Integer.parseInt(m.group(8)));
			content.setPayTime(payTime);
			content.setBankName(m.group(count));
		}
		
		return content;
	}
	
}
