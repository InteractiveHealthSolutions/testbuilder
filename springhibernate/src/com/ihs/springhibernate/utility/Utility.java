package com.ihs.springhibernate.utility;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility
{
	public static String DateToString(Date date)
	{
		// yyyy-MM-dd HH:mm:ss

		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = formatter.format(date);
		return stringDate;
	}

}
