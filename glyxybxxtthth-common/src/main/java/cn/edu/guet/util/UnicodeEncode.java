package cn.edu.guet.util;

public class UnicodeEncode {

	 public static String unicodeEncode(String string) {
	        char[] utfBytes = string.toCharArray();
	        String unicodeBytes = "";
	        for (int i = 0; i < utfBytes.length; i++) {
	            String hexB = Integer.toHexString(utfBytes[i]);
	            if (hexB.length() <= 2) {
	                hexB = "00" + hexB;
	            }
	            unicodeBytes = unicodeBytes + "\\u" + hexB;
	        }
	        return unicodeBytes;
	    }
}
