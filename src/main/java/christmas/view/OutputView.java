package christmas.view;

import static christmas.domain.constant.GiftMenu.*;
import static christmas.view.constant.OutputConstant.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;

import christmas.constant.Menu;
import christmas.domain.Benefit;

public class OutputView {
	private final StringBuilder eventResult;
	private final DecimalFormat decimalFormat = new DecimalFormat(PRICE_PATTERN.getValue());

	public OutputView() {
		eventResult = new StringBuilder();
	}

	public void stackPreviewMessage(LocalDate date) {// 미리보기
		eventResult.append(date.getMonthValue())
			.append("월 ")
			.append(date.getDayOfMonth())
			.append("일")
			.append(EVENT_PREVIEW_MSG.getValue())
			.append("\n");
	}

	public void stackOrder(Map<Menu, Integer> details) {// 주문 메뉴
		eventResult.append("\n").append(ORDERED_MENU_HEADER.getValue()).append("\n");
		for (Menu menu : details.keySet()) {
			eventResult.append(menu.getName()).append(" ").append(details.get(menu)).append("개\n");
		}
	}

	public void stackOrderPriceSum(long orderPriceSum) {// 할인 전 총주문 금액
		eventResult.append("\n").append(ORDER_PRICE_SUM_HEADER.getValue()).append("\n");
		eventResult.append(decimalFormat.format(orderPriceSum)).append(CURRENCY.getValue()).append("\n");
	}

	public void stackBenefit(Benefit benefit, long sumPriceAfterDiscount) {
		stackGiftMenu(benefit.getGiftMenuPrice() > 0);// 증정 메뉴
		stackBenefitDetails(benefit);// 혜택 내역
		stackSumBenefitPrice(benefit.getSumBenefitPrice());// 총혜택 금액
		stackSumPriceAfterDiscount(sumPriceAfterDiscount);// 할인 후 예상 결제 금액
	}

	public void stackBadge(String badgeName) {// 12월 이벤트 배지
		eventResult.append("\n").append(EVENT_BADGE_HEADER.getValue()).append("\n").append(badgeName);
	}

	private void stackGiftMenu(boolean isGift) {
		eventResult.append("\n").append(GIFT_MENU_HEADER.getValue()).append("\n");
		if (isGift) {
			eventResult.append(GIFT.getMenu().getName()).append(" ").append(GIFT.getCount()).append("개\n");
			return;
		}
		eventResult.append(NONE.getValue()).append("\n");
	}

	private void stackBenefitDetails(Benefit benefit) {
		eventResult.append("\n").append(BENEFIT_HEADER.getValue()).append("\n");
		stackDDayDiscount(benefit.getDDayDiscount());
		stackDailyDiscount(benefit.getWeekdayDiscount(), benefit.getWeekendDiscount());
		stackSpecialDiscount(benefit.getSpecialDiscount());
		stackGiftPrice(benefit.getGiftMenuPrice());
		if (benefit.isAllBlank()) {
			eventResult.append(NONE.getValue());
		}
		eventResult.append("\n");
	}

	private String getDiscountDetail(String msg, long discountPrice) {
		return msg + "-" + decimalFormat.format(discountPrice) + CURRENCY.getValue();
	}

	private void stackDDayDiscount(long dDayDiscount) {
		if (dDayDiscount > 0) {
			eventResult.append(getDiscountDetail(D_DAY_DISCOUNT_MSG.getValue(), dDayDiscount));
		}
	}

	private void stackDailyDiscount(long weekdayDiscount, long weekendDiscount) {
		if (weekdayDiscount > 0) {
			eventResult.append(getDiscountDetail(WEEKDAY_DISCOUNT_MSG.getValue(), weekdayDiscount));
		} else if (weekendDiscount > 0) {
			eventResult.append(getDiscountDetail(WEEKEND_DISCOUNT_MSG.getValue(), weekendDiscount));
		}
	}

	private void stackSpecialDiscount(long specialDiscount) {
		if (specialDiscount > 0) {
			eventResult.append(getDiscountDetail(SPECIAL_DISCOUNT_MSG.getValue(), specialDiscount));
		}
	}

	private void stackGiftPrice(long giftPrice) {
		if (giftPrice > 0) {
			eventResult.append(getDiscountDetail(GIFT_EVENT_MSG.getValue(), giftPrice));
		}
	}

	private void stackSumBenefitPrice(long sumBenefitPrice) {
		eventResult.append("\n").append(BENEFIT_PRICE_SUM_HEADER.getValue()).append("\n");
		if (sumBenefitPrice > 0) {
			eventResult.append("-");
		}
		eventResult.append(decimalFormat.format(sumBenefitPrice)).append(CURRENCY.getValue()).append("\n");
	}

	private void stackSumPriceAfterDiscount(long sumPriceAfterDiscount) {
		eventResult.append("\n").append(AFTER_DISCOUNT_HEADER.getValue()).append("\n")
			.append(decimalFormat.format(sumPriceAfterDiscount)).append(CURRENCY.getValue()).append("\n");
	}

	public void printResult() {
		System.out.print(eventResult);
	}

	public static void printError(String message) {
		System.out.println(message);
	}
}
