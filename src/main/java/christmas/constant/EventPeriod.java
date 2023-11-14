package christmas.constant;

import static christmas.constant.EventYearAndMonth.*;
import static christmas.utility.DateUtil.*;

import java.time.LocalDate;

public enum EventPeriod {
	EVENT_START_DATE(LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), 1)),

	EVENT_END_DATE(LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(),
		getLastDayOfMonth(EVENT_YEAR.getValue(), EVENT_MONTH.getValue())));

	private final LocalDate date;

	EventPeriod(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getDay() {
		return date.getDayOfMonth();
	}
}
