package christmas.exception;

public enum ErrorMessage {
	EMPTY_ERROR("[ERROR] 입력을 하지 않았습니다. 다시 입력해 주세요."),
	DAY_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
	ORDER_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
	SUM_PRICE_ERROR("[ERROR] 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다. 다시 입력해 주세요."),
	BEVERAGE_ERROR("[ERROR] 음료만 주문하실 수 없습니다. 다시 입력해 주세요."),
	ORDER_COUNT_ERROR("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
