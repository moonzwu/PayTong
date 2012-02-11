package com.ace.production.text;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SMSContentParser {

	public SMSContent parse(String data) {
		SMSContent content = null;
		if (data.contains("广发银行")) {
			content = generateGdbBankSMSContent(data);
		}
		if (data.contains("招商银行")) {
			content = generateCmbBankSMSContent(data);
		}
		
		return content;
	}

	private SMSContent generateGdbBankSMSContent(String data) {
		SMSContent content = new SMSContent();
		Pattern p = Pattern.compile("您尾号(\\d{4})广发卡(\\d{2})月" +
				"(人民币)账单金额(-?\\d+\\.\\d+)?，" +
				"最低还款(-?\\d+\\.\\d+)?，" +
				"还款到期(\\d{2})月(\\d{2})日。" +
				"请留意电子账单，若已还勿理会【(\\W{4})】");
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
		Pattern p = Pattern.compile("(\\W+)先生，您招行个人信用卡(\\d{2})月" +
				"账单金额(人民币)(-?\\d+\\,\\d+\\.\\d+)?，(美金)(-?\\d+\\.\\d+)?。" +
				"到期还款日(\\d{2})月(\\d{2})日\\[(\\W{4})\\]");
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
