package christmas.service;

import static christmas.constant.EventYearAndMonth.*;
import static christmas.domain.constant.BenefitConstant.*;
import static christmas.view.constant.OutputConstant.*;
import static java.time.DayOfWeek.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import christmas.constant.Menu;
import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.dto.BenefitInfo;
import christmas.repository.EventRepository;

public class EventService {
	private final EventRepository eventRepository;

	public EventService() {
		this.eventRepository = new EventRepository();
	}

	public void saveReservation(VisitDate visitDate, Order order) {
		eventRepository.saveVisitDate(visitDate);
		eventRepository.saveOrder(order);
		eventRepository.saveBenefit(new Benefit(visitDate, order, getBenefitInfo(visitDate)));
		eventRepository.saveBadge(calculateBadge());
	}

	private BenefitInfo getBenefitInfo(VisitDate visitDate) {
		boolean isDDayOrBefore = ChronoUnit.DAYS.between(visitDate.getDate(),
			LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), (int)D_DAY.getValue())) >= 0;
		boolean isWeekDay = !visitDate.getDayOfTheWeek().equals(SUNDAY) && !visitDate.getDayOfTheWeek().equals(MONDAY);// 일,월이 아닐 경우 평일
		boolean isSpecialDay = visitDate.getDayOfTheWeek().equals(SUNDAY) || visitDate.getDay() == D_DAY.getValue();// 일요일, 25일일 경우 별 뱃지
		boolean isQualifiedForGift = eventRepository.getSumPriceBeforeDiscount() >= GIFT_QUALIFYING_PRICE.getValue();
		return new BenefitInfo(isDDayOrBefore, isWeekDay, isSpecialDay, isQualifiedForGift);
	}

	private Badge calculateBadge() {
		return new Badge(eventRepository.findAllBenefit().getSumBenefitPrice());
	}

	public LocalDate getVisitDate() {
		return eventRepository.getVisitDate();
	}

	public Map<Menu, Integer> getOrder() {
		return eventRepository.findAllOrderDetails();
	}

	public long getSumPriceBeforeDiscount() {
		return eventRepository.getSumPriceBeforeDiscount();
	}

	public Benefit getBenefit() {
		return eventRepository.findAllBenefit();
	}

	public long getSumPriceAfterDiscount() {
		return eventRepository.getSumPriceAfterDiscount();
	}

	public String getBadgeName() {
		return eventRepository.getBadgeName().orElse(NONE.getValue());
	}
}
