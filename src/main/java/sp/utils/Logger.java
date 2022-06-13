package sp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public static void print(String message) {
		System.out.println("[" + sdf.format(new Date()) + "] " + message);
	}
	
	public static void print(Object message) {
		System.out.println("[" + sdf.format(new Date()) + "] " + message);
	}
}
