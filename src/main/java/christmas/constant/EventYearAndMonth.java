package christmas.constant;

public enum EventYearAndMonth {
	EVENT_YEAR(2023),
	EVENT_MONTH(12);
	private final int value;

	EventYearAndMonth(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
