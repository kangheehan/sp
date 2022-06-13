package sp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	// 현재시각
	public static String getCurrentDate() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}
	
	// 
	public static Date toDate(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.parse(dateString);
	}

	public static LocalDateTime toLocalDateTime(Date dt) {
		LocalDateTime ldt = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return ldt;
	}
	
	public static void main(String[] args) throws Exception {
		String current = DateUtils.getCurrentDate();
		System.out.println(current);
		System.out.println(toDate(current));
	}
}
