package christmas.domain;

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
		return validateMenu(map);
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

	private void validateCount(Map<String, Integer> map) throws IllegalArgumentException {
		int countSum = 0;
		for (int count : map.values()) {
			countSum += count;
			if (count <= 0 || countSum > ORDER_AMOUNT_LIMIT.getValue()) {
				throw new IllegalArgumentException(ORDER_ERROR.getMessage());
			}
		}
	}
}
