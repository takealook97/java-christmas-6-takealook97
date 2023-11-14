package christmas.view.constant;

import static christmas.constant.EventYearAndMonth.*;

public enum InputConstant {
	INIT_MSG("안녕하세요! 우테코 식당 " + EVENT_MONTH.getValue() + "월 이벤트 플래너입니다."),
	DAY_REQUEST(EVENT_MONTH.getValue() + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
	ORDER_REQUEST("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
	INPUT_MENU_DELIMITER(","),
	MENU_COUNT_SEPARATOR("-");

	private final String value;

	InputConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
