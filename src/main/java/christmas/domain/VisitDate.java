package christmas.domain;

import static christmas.constant.EventPeriod.*;
import static christmas.constant.EventYearAndMonth.*;
import static christmas.exception.ErrorMessage.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
	private final LocalDate date;

	public VisitDate(int day) {
		validate(day);
		this.date = LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), day);
	}

	public LocalDate getDate() {
		return date;
	}

	public int getDay() {
		return date.getDayOfMonth();
	}

	public DayOfWeek getDayOfTheWeek() {
		return date.getDayOfWeek();
	}

	private void validate(int day) throws IllegalArgumentException {
		if (day < EVENT_START_DATE.getDay() || EVENT_END_DATE.getDay() < day) {
			throw new IllegalArgumentException(DAY_ERROR.getMessage());
		}
	}
}
