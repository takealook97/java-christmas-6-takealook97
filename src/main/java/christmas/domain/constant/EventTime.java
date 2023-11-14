package christmas.domain.constant;

import static christmas.utility.DateUtil.*;

public enum EventTime {
	EVENT_YEAR(2023),
	EVENT_MONTH(12),
	EVENT_START_DAY(1),
	EVENT_END_DAY(getLastDayOfMonth(EVENT_YEAR.getValue(), EVENT_MONTH.value));
	private final int value;

	EventTime(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
