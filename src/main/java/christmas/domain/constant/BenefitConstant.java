package christmas.domain.constant;

import static christmas.constant.EventTime.*;

public enum BenefitConstant {
	D_DAY(25),
	DISCOUNT_INIT_PRICE(1000),
	DAILY_DISCOUNT_INCREMENT(100),
	STAR_DISCOUNT(1000),
	GIFT_QUALIFYING_PRICE(120000),
	YEAR_BASED_DISCOUNT(EVENT_YEAR.getValue());
	
	private final long value;

	BenefitConstant(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}
}
