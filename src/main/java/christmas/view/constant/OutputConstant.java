package christmas.view.constant;

import static christmas.domain.constant.EventTime.*;

public enum OutputConstant {
	EVENT_PREVIEW_MSG("에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
	ORDERED_MENU_HEADER("<주문 메뉴>"),
	ORDER_PRICE_SUM_HEADER("<할인 전 총주문 금액>"),
	GIFT_MENU_HEADER("<증정 메뉴>"),
	BENEFIT_HEADER("<혜택 내역>"),
	D_DAY_DISCOUNT_MSG("크리스마스 디데이 할인: "),
	WEEK_DAY_DISCOUNT_MSG("평일 할인: "),
	WEEKEND_DISCOUNT_MSG("주말 할인: "),
	SPECIAL_DISCOUNT_MSG("특별 할인: "),
	GIFT_EVENT_MSG("증정 이벤트: "),
	BENEFIT_PRICE_SUM_HEADER("<총혜택 금액>"),
	AFTER_DISCOUNT_HEADER("<할인 후 예상 결제 금액>"),
	EVENT_BADGE_HEADER("<" + EVENT_MONTH + "월 이벤트 배지>"),
	NONE("없음"),
	PRICE_PATTERN("#,###"),
	CURRENCY("원");

	private final String value;

	OutputConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
