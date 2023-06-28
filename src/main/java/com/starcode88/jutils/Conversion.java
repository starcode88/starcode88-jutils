package com.starcode88.jutils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class Conversion {

	/**
	 * char array which will be used for conversion of data.
	 */
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] hexStringToByteArray(String s) {
		s = s.replaceAll("0x" , "");

		// Following section can be optimized by using char[]
		s = s.replaceAll(" ", "");
		s = s.replaceAll(",", "");
		s = s.replaceAll("\\.", "");
		s = s.replaceAll(";", "");
		//-----------------------------------
		
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	
	/**
	 * Reads the whole data from the InputStream and returns it as a String
	 * @param inputStream
	 * @return
	 */
	public static String getString(InputStream inputStream) {
		 try (Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
			 return s.hasNext() ? s.next() : "";
		 }
	}
	
	/**
	 * Encodes a string as base64 encoding
	 * @param string
	 * @return
	 */
	public static String encodeAsBase64(String string) {
		return Base64.getEncoder().encodeToString(string.getBytes(StandardCharsets.UTF_8));
	}
}