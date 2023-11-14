package christmas.constant;

public enum Category {
	APPETIZER("애피타이저"),
	MAIN("메인"),
	DESSERT("디저트"),
	BEVERAGE("음료");

	private final String name;

	Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
