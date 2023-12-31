package christmas.repository;

import static christmas.view.constant.OutputConstant.*;

import java.time.LocalDate;
import java.util.Map;

import christmas.constant.Menu;
import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.domain.VisitDate;

public class EventRepository {
	private VisitDate visitDate;
	private Order order;
	private Benefit benefit;
	private Badge badge;

	public void saveVisitDate(VisitDate visitDate) {
		this.visitDate = visitDate;
	}

	public void saveOrder(Order order) {
		this.order = order;
	}

	public void saveBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	public void saveBadge(Badge badge) {
		this.badge = badge;
	}

	public LocalDate getVisitDate() {
		return visitDate.getDate();
	}

	public Map<Menu, Integer> findAllOrderDetails() {
		return order.getDetails();
	}

	public long getSumPriceBeforeDiscount() {
		return order.getPriceSum();
	}

	public Benefit findAllBenefit() {
		return benefit;
	}

	public long getSumPriceAfterDiscount() {
		return order.getPriceSum() - benefit.getSumDiscount();
	}

	public String getBadgeName() {
		if (badge == null || badge.getName() == null) {
			return NONE.getValue();
		}
		return badge.getName();
	}
}
