package christmas.utility;

import java.time.YearMonth;

public class DateUtil {
	public static int getLastDayOfMonth(int year, int month) {
		return YearMonth.of(year, month).atEndOfMonth().getDayOfMonth();
	}
}
