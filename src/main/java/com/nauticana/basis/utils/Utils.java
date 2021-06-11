package com.nauticana.basis.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Utils {
	
	public static final long dayMilliSeconds = 24 * 60 * 60 * 1000;
	
	public static boolean emptyStr(String s) {
		if (s == null)
			return true;
		if (s.isEmpty())
			return true;
		if (s.length() == 0)
			return true;
		return false;
	}
	
	public static Date onlyDate() {
//		return new Date((System.currentTimeMillis() / dayMilliSeconds ) * dayMilliSeconds);
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date maxDate() {
		try {
			return Labels.dmyDF.parse("31/12/9999");
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String camelCase(String s) {
		String[] x = s.toLowerCase(Locale.ENGLISH).split("_");
		String   l = x[0];
		
		for (int i = 1; i < x.length; i++) {
			byte b = (byte)x[i].charAt(0);
			char c = (char) (b-32);
			l = l + c + x[i].substring(1);
		}
		return l;
	}

	public static String titleCase(String s) {
		String[] x = s.toLowerCase(Locale.ENGLISH).split("_");
		String l = "";
		
		for (int i = 0; i < x.length; i++) {
			byte b = (byte)x[i].charAt(0);
			char c = (char) (b-32);
			l = l + c + x[i].substring(1);
		}
		return l;
	}

	public static String clearGet(String s) {
		byte b = (byte)s.charAt(3);
		char c = (char) (b+32);
		return c + s.substring(4);
	}

	public static boolean runSQL(String sqltext, Connection conn, PrintWriter writer) {
		Statement stmt1 = null;
		try {
			stmt1 = conn.createStatement();
			stmt1.execute(sqltext);
			stmt1.close();
			stmt1 = null;
			return true;
		} catch (Exception e) {
			System.out.println(sqltext);
			e.printStackTrace();
			if (writer != null) {
				writer.write(sqltext);
				e.printStackTrace(writer);
			}
		} finally {
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
					if (writer != null) {
						writer.write(sqltext);
						e.printStackTrace(writer);
					}
				}
				stmt1 = null;
			}
		}
		return false;
	}

	public static void runSQLFile(String filename, Connection conn, String encoding) throws IOException {
//		File sqlfile = new File(filename);
		File logfile = new File(filename + ".log");
//		BufferedReader reader = null;
		PrintWriter writer = null;

		String sep = System.getProperty("line.separator");
		String text = "";
		String sqltext = "";
		
		String enc = System.getProperty("file.encoding");
		if (!emptyStr(encoding)) enc = encoding;
		Scanner reader = new Scanner(new FileInputStream(filename), enc);
		
//		reader = new BufferedReader(new FileReader(sqlfile));
		writer = new PrintWriter(new FileWriter(logfile));
		while (text != null) {
			text = text.trim();
			if (!emptyStr(text)) {
				if (text.charAt(text.length() - 1) == ';') {
					sqltext = sqltext + " " + text.substring(0, text.length() - 1);
					text = "/";
				}
				if (text.equals("/")) {
					if (!runSQL(sqltext, conn, writer)) {
						sqltext = "SQL error:" + sep + sqltext;
						writer.write(sqltext + sep);
						writer.close();
					} else {
						writer.write(sqltext + sep);
						sqltext = "";
					}
				} else
					sqltext = sqltext + " " + text;
			}
			try {
				text = reader.nextLine();
			} catch (Exception e) {
				text = null;
			}
		}
		if (reader != null) {
			reader.close();
		}
		if (writer != null) {
			writer.close();
		}
	}

	public static short[] nextTermId(char termType, short year, short termId) {
		short[] r = new short[2];
		if (year == 0)
			r[0] = (short) Calendar.getInstance().get(Calendar.YEAR);
		else
			r[0] = year;
		
		r[1] = (short) (termId+1);
		if ((termType == 'D' && termId == 365) ||
			(termType == 'E' && termId == 52) ||
			(termType == 'M' && termId == 12) ||
			(termType == 'Q' && termId == 4) ||
			(termType == 'Y') ) {
			r[0]++;
			r[1] = 1;
		}
		return r;
	}
}
