package christmas.controller;

import static christmas.exception.ErrorMessage.*;
import static christmas.utility.InputValidation.*;
import static christmas.view.OutputView.*;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.constant.InputConstant;

public class EventController {
	private final InputView inputView;
	private final OutputView outputView;
	private final EventService eventService;

	public EventController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.eventService = new EventService();
	}

	public void run() {
		initEvent();
		getResult();
	}

	private void initEvent() {
		inputView.printInit();
		inputView.printDayRequest();
		VisitDate visitDate = getDayInput();
		inputView.printOrderRequest();
		Order order = getOrderInput();
		eventService.saveReservation(visitDate, order);
	}

	private void getResult() {
		outputView.stackPreviewMessage(eventService.getVisitDate());
		outputView.stackOrder(eventService.getOrder());
		outputView.stackOrderPriceSum(eventService.getSumPriceBeforeDiscount());
		outputView.stackBenefit(eventService.getBenefit(), eventService.getSumPriceAfterDiscount());
		outputView.stackBadge(eventService.getBadgeName());
		outputView.printResult();
	}

	private VisitDate getDayInput() {
		try {
			String input = inputView.getInput();
			return new VisitDate(Integer.parseInt(input));
		} catch (NumberFormatException e) {
			printError(DAY_ERROR.getMessage());
			return getDayInput();
		} catch (IllegalArgumentException e) {
			printError(e.getMessage());
			return getDayInput();
		}
	}

	private Order getOrderInput() {
		try {
			String[] pairs = inputView.getInput().split(InputConstant.INPUT_MENU_DELIMITER.getValue());
			return new Order(validatePairs(pairs));
		} catch (IllegalArgumentException e) {
			printError(e.getMessage());
			return getOrderInput();
		}
	}
}
