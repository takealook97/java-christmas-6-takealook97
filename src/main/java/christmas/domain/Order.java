package christmas.domain;

import static christmas.constant.Category.*;
import static christmas.domain.constant.OrderConstant.*;
import static christmas.exception.ErrorMessage.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import christmas.constant.Category;
import christmas.constant.Menu;

public class Order {
	private final Map<Menu, Integer> details;

	public Order(Map<String, Integer> map) {
		this.details = validate(map);
	}

	public Map<Menu, Integer> getDetails() {
		return details;
	}

	public long getPriceSum() {
		return details.entrySet()
			.stream()
			.mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
			.sum();
	}

	public long getCategoryCount(Category category) {
		return details.entrySet()
			.stream()
			.filter(entry -> entry.getKey().getCategory() == category)
			.mapToLong(Map.Entry::getValue)
			.sum();
	}

	private Map<Menu, Integer> validate(Map<String, Integer> map) throws IllegalArgumentException {
		validateCount(map);
		Map<Menu, Integer> order = validateMenu(map);
		validateBeverage(order);
		return order;
	}

	private void validateCount(Map<String, Integer> map) throws IllegalArgumentException {
		int countSum = 0;
		for (int count : map.values()) {
			countSum += count;
			if (count <= 0) {
				throw new IllegalArgumentException(ORDER_ERROR.getMessage());
			} else if (countSum > ORDER_AMOUNT_LIMIT.getValue()) {
				throw new IllegalArgumentException(ORDER_COUNT_ERROR.getMessage());
			}
		}
	}

	private Map<Menu, Integer> validateMenu(Map<String, Integer> map) throws IllegalArgumentException {
		return map.entrySet()
			.stream()
			.collect(Collectors.toMap(
				entry -> Menu.of(entry.getKey()),
				Map.Entry::getValue,
				(e1, e2) -> e1,
				LinkedHashMap::new
			));
	}

	private void validateBeverage(Map<Menu, Integer> order) throws IllegalArgumentException {
		boolean allBeverages = order.keySet().stream()
			.allMatch(menu -> menu.getCategory() == BEVERAGE);
		if (allBeverages) {
			throw new IllegalArgumentException(BEVERAGE_ERROR.getMessage());
		}
	}
}
