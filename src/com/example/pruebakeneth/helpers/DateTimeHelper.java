package com.example.pruebakeneth.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
@SuppressLint("SimpleDateFormat")
public class DateTimeHelper {
	// Year:
	// YYYY (eg 1997)
	// Year and month:
	// YYYY-MM (eg 1997-07)
	// Complete date:
	// YYYY-MM-DD (eg 1997-07-16)
	// Complete date plus hours and minutes:
	// YYYY-MM-DDThh:mmTZD (eg 1997-07-16T19:20+01:00)
	// Complete date plus hours, minutes and seconds:
	// YYYY-MM-DDThh:mm:ssTZD (eg 1997-07-16T19:20:30+01:00)
	// Complete date plus hours, minutes, seconds and a decimal fraction of a
	// second
	// YYYY-MM-DDThh:mm:ss.sTZD (eg 1997-07-16T19:20:30.45+01:00)

	// where:

	// YYYY = four-digit year
	// MM = two-digit month (01=January, etc.)
	// DD = two-digit day of month (01 through 31)
	// hh = two digits of hour (00 through 23) (am/pm NOT allowed)
	// mm = two digits of minute (00 through 59)
	// ss = two digits of second (00 through 59)
	// s = one or more digits representing a decimal fraction of a second
	// TZD = time zone designator (Z or +hh:mm or -hh:mm)
	public static Date parse(String input) throws java.text.ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(input);

	}

	public static Long parseMili(String input) throws java.text.ParseException {
		if (input == null || input.equals("")) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(
					"1900-01-01 00:00:00.0").getTime();
		} else {
			if (input.length() > 21) {
				input = input.substring(0, 21);
			}
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(input)
					.getTime();
		}

	}

	public static String toString(Long datetime) {
		String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
				.format(new Date(datetime));
		result = result.substring(0, result.lastIndexOf(".") + 2);
		return result;

	}
}
