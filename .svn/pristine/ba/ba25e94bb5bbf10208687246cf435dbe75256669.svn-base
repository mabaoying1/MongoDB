package com.healthpay.common.utils;

import java.security.MessageDigest;

/**
 * @author mabaoying
 * @ClassName: MD5Utils
 * @Description: TODO
 * @date: 2019/8/21
 * @最后修改人:
 * @最后修改时间:
 */
public class MD5Utils {
	public static String toMD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(toMD5("2009ms1234"));
		System.out.println(toMD5("13258296056" + "yrb-app"));
	}
}
