package christmas.domain.constant;

public enum OrderConstant {
	EVENT_INIT_PRICE(10000),
	ORDER_AMOUNT_LIMIT(20);

	private final long value;

	OrderConstant(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}
}
