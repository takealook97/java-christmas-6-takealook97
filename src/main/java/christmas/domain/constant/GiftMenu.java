package christmas.domain.constant;

import static christmas.constant.Menu.*;

import christmas.constant.Menu;

public enum GiftMenu {
	GIFT(CHAMPAGNE, 1);

	private final Menu menu;
	private final long count;

	GiftMenu(Menu menu, long count) {
		this.menu = menu;
		this.count = count;
	}

	public Menu getMenu() {
		return menu;
	}

	public long getCount() {
		return count;
	}
}
