package christmas.domain;

import static christmas.constant.Category.*;
import static christmas.exception.ErrorMessage.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class OrderTest {
	@Test
	void 주문_합계_계산_테스트() {
		Map<String, Integer> orderMap = new HashMap<>();
		orderMap.put("양송이수프", 2);
		orderMap.put("타파스", 1);
		orderMap.put("티본스테이크", 1); // 가격 합계 이벤트 시작 가격 이상

		Order order = new Order(orderMap);

		long expectedTotal = 6000 * 2 + 5500 + 55000;
		assertEquals(expectedTotal, order.getPriceSum());
	}

	@Test
	void 카테고리별_주문_개수_테스트() {
		Map<String, Integer> orderMap = new HashMap<>();
		orderMap.put("양송이수프", 1);
		orderMap.put("타파스", 1);
		orderMap.put("티본스테이크", 2);

		Order order = new Order(orderMap);

		long appetizerCount = order.getCategoryCount(APPETIZER);
		long mainCount = order.getCategoryCount(MAIN);

		assertEquals(2, appetizerCount); // 양송이수프 1, 타파스 1
		assertEquals(2, mainCount); // 티본스테이크 2
	}

	@Test
	void 주문_개수_유효성_검사_테스트() {
		Map<String, Integer> orderMap = new HashMap<>();
		orderMap.put("양송이수프", 0); // 유효하지 않은 수량

		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(orderMap));
		assertEquals(ORDER_ERROR.getMessage(), exception.getMessage());
	}

	@Test
	void 주문_항목_유효성_검사_테스트() {
		Map<String, Integer> orderMap = new HashMap<>();
		orderMap.put("존재하지않는메뉴", 1); // 유효하지 않은 메뉴

		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(orderMap));
		assertEquals(ORDER_ERROR.getMessage(), exception.getMessage());
	}

	@Test
	void 주문_음료만_포함_유효성_검사_테스트() {
		Map<String, Integer> orderMap = new HashMap<>();
		orderMap.put("제로콜라", 2);
		orderMap.put("레드와인", 1); // 주문이 음료만 포함

		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(orderMap));
		assertEquals(BEVERAGE_ERROR.getMessage(), exception.getMessage());
	}

	@Test
	void 유효한_주문_테스트() {
		Map<String, Integer> orderMap = new HashMap<>();
		orderMap.put("티본스테이크", 1); // 유효한 주문 (메인 카테고리, 적절한 가격)

		assertDoesNotThrow(() -> new Order(orderMap));
	}
}
