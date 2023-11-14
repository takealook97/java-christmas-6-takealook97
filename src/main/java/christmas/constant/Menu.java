package christmas.constant;

import static christmas.constant.Category.*;
import static christmas.exception.ErrorMessage.*;

import java.util.Arrays;

public enum Menu {
	MUSHROOM_SOUP(APPETIZER, "양송이수프", 6000),
	TAPAS(APPETIZER, "타파스", 5500),
	CAESAR_SALAD(APPETIZER, "시저샐러드", 8000),
	T_BONE_STEAK(MAIN, "티본스테이크", 55000),
	BARBECUE_RIBS(MAIN, "바비큐립", 54000),
	SEAFOOD_PASTA(MAIN, "해산물파스타", 35000),
	CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25000),
	CHOCOLATE_CAKE(DESSERT, "초코케이크", 15000),
	ICE_CREAM(DESSERT, "아이스크림", 5000),
	ZERO_COLA(BEVERAGE, "제로콜라", 3000),
	RED_WINE(BEVERAGE, "레드와인", 60000),
	CHAMPAGNE(BEVERAGE, "샴페인", 25000);
	private final Category category;
	private final String name;
	private final long price;

	Menu(Category category, String name, long price) {
		this.category = category;
		this.name = name;
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
		return price;
	}

	public Menu of(String name) {
		return Arrays.stream(Menu.values())
			.filter(menu -> menu.getName().equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ORDER_ERROR.getMessage()));
	}
}
