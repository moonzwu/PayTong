package com.ace.production.banks;

import java.util.HashMap;

public class NumberBankTable {
	private static NumberBankTable instance = new NumberBankTable();
	private HashMap<String, String> mapping = new HashMap<String, String>();
	
	public static NumberBankTable getInstance() {
		return instance;
	}
	
	private NumberBankTable() {
		mapping.put("95588", "�㷢����");
		mapping.put("95566", "��������");
	}
	
	public String getBankNameByNumber(String number) {
		if (mapping.containsKey(number)) {
			return mapping.get(number);
		}
		return "";
	}
}
