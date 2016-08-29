package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	private static String plusTuThang(String tuThang) {
		if (tuThang.substring(4, 6).equals("12")) {
			tuThang = String.valueOf(Integer.parseInt(tuThang) + 89);
		} else {
			tuThang = String.valueOf(Integer.parseInt(tuThang) + 1);
		}
		return tuThang;
	}
	public static void main (String []args) {
		System.out.println(plusTuThang("299912"));
	}
}
