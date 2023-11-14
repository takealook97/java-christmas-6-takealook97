package christmas.domain;

import static christmas.domain.constant.BenefitConstant.*;
import static christmas.domain.constant.GiftMenu.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import christmas.dto.BenefitInfo;

class BenefitTest {
	@Test
	void 디데이_할인_계산_테스트() {
		VisitDate visitDate = new VisitDate(24);
		Order order = new Order(new HashMap<>());
		BenefitInfo benefitInfo = new BenefitInfo(true, false, false, false);

		Benefit benefit = new Benefit(visitDate, order, benefitInfo);

		long expectedDDayDiscount =
			DISCOUNT_INIT_PRICE.getValue() + (23 * DAILY_DISCOUNT_INCREMENT.getValue());
		assertEquals(expectedDDayDiscount, benefit.getDDayDiscount());
	}

	@Test
	void 주중_할인_계산_테스트() {
		VisitDate visitDate = new VisitDate(18);// 월요일
		Map<String, Integer> orderDetails = new HashMap<>();
		orderDetails.put("초코케이크", 2); // 디저트 카테고리
		Order order = new Order(orderDetails);
		BenefitInfo benefitInfo = new BenefitInfo(false, true, false, false);

		Benefit benefit = new Benefit(visitDate, order, benefitInfo);

		long expectedWeekdayDiscount = 2 * YEAR_BASED_DISCOUNT.getValue(); // 디저트 개수에 따른 할인
		assertEquals(expectedWeekdayDiscount, benefit.getWeekdayDiscount());
	}

	@Test
	void 주말_할인_계산_테스트() {
		VisitDate visitDate = new VisitDate(17); // 일요일
		Map<String, Integer> orderDetails = new HashMap<>();
		orderDetails.put("티본스테이크", 1); // 메인 카테고리
		Order order = new Order(orderDetails);
		BenefitInfo benefitInfo = new BenefitInfo(false, false, false, false);

		Benefit benefit = new Benefit(visitDate, order, benefitInfo);

		long expectedWeekendDiscount = YEAR_BASED_DISCOUNT.getValue(); // 메인 개수에 따른 할인
		assertEquals(expectedWeekendDiscount, benefit.getWeekendDiscount());
	}

	@Test
	void 특별_할인_계산_테스트() {
		VisitDate visitDate = new VisitDate(25);
		Order order = new Order(new HashMap<>());
		BenefitInfo benefitInfo = new BenefitInfo(false, false, true, false);

		Benefit benefit = new Benefit(visitDate, order, benefitInfo);

		long expectedSpecialDiscount = STAR_DISCOUNT.getValue(); // 특별 할인
		assertEquals(expectedSpecialDiscount, benefit.getSpecialDiscount());
	}

	@Test
	void 선물_메뉴_가격_계산_테스트() {
		VisitDate visitDate = new VisitDate(25);
		Order order = new Order(new HashMap<>());
		BenefitInfo benefitInfo = new BenefitInfo(false, false, false, true);

		Benefit benefit = new Benefit(visitDate, order, benefitInfo);

		long expectedGiftMenuPrice = GIFT.getPrice(); // 선물 메뉴 가격
		assertEquals(expectedGiftMenuPrice, benefit.getGiftMenuPrice());
	}

	@Test
	void 총_할인_및_혜택_가격_계산_테스트() {
		VisitDate visitDate = new VisitDate(24); // 이벤트 시작일로부터의 날짜 차이를 기반으로 함
		Map<String, Integer> orderDetails = new HashMap<>();
		orderDetails.put("초코케이크", 2); // 디저트 카테고리
		orderDetails.put("티본스테이크", 1); // 메인 카테고리
		Order order = new Order(orderDetails);
		BenefitInfo benefitInfo = new BenefitInfo(true, true, true, true);

		Benefit benefit = new Benefit(visitDate, order, benefitInfo);

		long expectedSumDiscount =
			DISCOUNT_INIT_PRICE.getValue() + (23 * DAILY_DISCOUNT_INCREMENT.getValue()) // D-Day 할인
				+ 2 * YEAR_BASED_DISCOUNT.getValue() // 주중 디저트 할인
				+ STAR_DISCOUNT.getValue(); // 특별 할인
		long expectedSumBenefitPrice = expectedSumDiscount + GIFT.getPrice(); // 총 혜택 가격

		assertEquals(expectedSumDiscount, benefit.getSumDiscount());
		assertEquals(expectedSumBenefitPrice, benefit.getSumBenefitPrice());
	}
}