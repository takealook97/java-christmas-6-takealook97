package christmas.service;

import static christmas.constant.Category.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import christmas.constant.Menu;
import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.domain.VisitDate;

public class EventServiceTest {
	private EventService eventService;

	@BeforeEach
	public void setup() {
		eventService = new EventService();
	}

	@Test
	public void 예약정보_저장_및_검색() {
		VisitDate visitDate = new VisitDate(25);
		Map<String, Integer> map = new HashMap<>();
		map.put("양송이수프", 2);
		Order order = new Order(map);

		eventService.saveReservation(visitDate, order);

		LocalDate savedVisitDate = eventService.getVisitDate();
		assertEquals(visitDate.getDate(), savedVisitDate);
	}

	@Test
	public void 주문정보_저장_및_검색() {
		Map<String, Integer> map = new HashMap<>();
		map.put("양송이수프", 2);
		map.put("바비큐립", 1);
		Order order = new Order(map);

		eventService.saveReservation(new VisitDate(25), order);

		Map<Menu, Integer> savedOrder = eventService.getOrder();
		long sumPriceBeforeDiscount = eventService.getSumPriceBeforeDiscount();

		assertEquals(map.size(), savedOrder.size());
		assertTrue(savedOrder.containsKey(Menu.MUSHROOM_SOUP));
		assertTrue(savedOrder.containsKey(Menu.BARBECUE_RIBS));
		assertEquals(map.get("양송이수프"), savedOrder.get(Menu.MUSHROOM_SOUP));
		assertEquals(map.get("바비큐립"), savedOrder.get(Menu.BARBECUE_RIBS));
		assertEquals(6000 * 2 + 54000, sumPriceBeforeDiscount);
	}

	@Test
	public void 할인정보_저장_및_검색() {
		VisitDate visitDate = new VisitDate(19);
		Map<String, Integer> map = new HashMap<>();
		map.put("초코케이크", 2);
		Order order = new Order(map);

		eventService.saveReservation(visitDate, order);
		Benefit savedBenefit = eventService.getBenefit();
		assertTrue(savedBenefit.getDDayDiscount() > 0);
		assertTrue(savedBenefit.getWeekdayDiscount() > 0);
		assertEquals(0, savedBenefit.getWeekendDiscount());
		assertTrue(savedBenefit.getSpecialDiscount() == 0);
		assertEquals(0, savedBenefit.getGiftMenuPrice());
	}

	@Test
	public void 뱃지정보_저장_및_검색() {
		VisitDate visitDate = new VisitDate(25);
		Map<String, Integer> map = new HashMap<>();
		map.put("티본스테이크", 10);
		Order order = new Order(map);

		eventService.saveReservation(visitDate, order);

		String savedBadgeName = eventService.getBadgeName();
		System.out.println(savedBadgeName);

		assertEquals("산타", savedBadgeName);
	}
}
