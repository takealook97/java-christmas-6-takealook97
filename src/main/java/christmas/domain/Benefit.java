package christmas.domain;

import static christmas.constant.Category.*;
import static christmas.constant.EventPeriod.*;
import static christmas.domain.constant.BenefitConstant.*;
import static christmas.domain.constant.GiftMenu.*;

import java.time.temporal.ChronoUnit;

import christmas.dto.BenefitInfo;

public class Benefit {
	private long dDayDiscount;
	private long weekdayDiscount;
	private long weekendDiscount;
	private long specialDiscount;
	private long giftMenuPrice;

	public Benefit(VisitDate visitDate, Order order, BenefitInfo benefitInfo) {
		setDDayDiscount(visitDate, benefitInfo.isDDayOrBefore());
		setDailyDiscount(order, benefitInfo.isWeekday());
		setSpecialDiscount(benefitInfo.isSpecialDay());
		setGiftMenuPrice(benefitInfo.isQualifiedForGift());
	}

	private void setDDayDiscount(VisitDate visitDate, boolean isDDayOrBefore) {
		if (isDDayOrBefore) {
			long dayGap = ChronoUnit.DAYS.between(EVENT_START_DATE.getDate(), visitDate.getDate());
			this.dDayDiscount = DISCOUNT_INIT_PRICE.getValue() + (dayGap * DAILY_DISCOUNT_INCREMENT.getValue());
		}
	}

	private void setDailyDiscount(Order order, boolean isWeekday) {
		long dessertCount = order.getCategoryCount(DESSERT);
		long mainCount = order.getCategoryCount(MAIN);
		if (isWeekday && dessertCount > 0) {
			this.weekdayDiscount = dessertCount * YEAR_BASED_DISCOUNT.getValue();
		} else if (!isWeekday && mainCount > 0) {
			this.weekendDiscount = mainCount * YEAR_BASED_DISCOUNT.getValue();
		}

	}

	private void setSpecialDiscount(boolean isSpecialDay) {
		if (isSpecialDay) {
			this.specialDiscount = STAR_DISCOUNT.getValue();
		}
	}

	private void setGiftMenuPrice(boolean isQualifiedForGift) {
		if (isQualifiedForGift) {
			this.giftMenuPrice = GIFT.getPrice();
		}
	}

	public long getDDayDiscount() {
		return dDayDiscount;
	}

	public long getWeekdayDiscount() {
		return weekdayDiscount;
	}

	public long getWeekendDiscount() {
		return weekendDiscount;
	}

	public long getSpecialDiscount() {
		return specialDiscount;
	}

	public long getGiftMenuPrice() {
		return giftMenuPrice;
	}

	public long getSumDiscount() {
		return dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
	}

	public long getSumBenefitPrice() {
		return dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giftMenuPrice;
	}

	public boolean isAllBlank() {
		return dDayDiscount == 0 && weekdayDiscount == 0 && weekendDiscount == 0 && specialDiscount == 0 && giftMenuPrice == 0;
	}
}
