package com.boco.soap.variant.common;

/**
 * @author
 * @Description 淇′护鐐瑰崄鍏繘鍒�
 */
public class SPC16 {
	/**
	 * *
	 * 
	 * @param
	 * @return
	 */
	public static String spc10to16(String number) {
		if (number.indexOf(',') > 0)
			number = number.split("\\,")[0];
		String[] num = number.replace('-', '.').split("\\.");

		String num0 = Integer.toHexString(Integer.parseInt(num[0]));
		String num1 = Integer.toHexString(Integer.parseInt(num[1]));
		String num2 = Integer.toHexString(Integer.parseInt(num[2]));
		if (num0.length() < 2) {
			num0 = "0" + num0;
		}
		if (num1.length() < 2) {
			num1 = "0" + num1;
		}
		if (num2.length() < 2) {
			num2 = "0" + num2;
		}
		String spc16 = num0 + num1 + num2;

		return spc16.toUpperCase();
	}

}
