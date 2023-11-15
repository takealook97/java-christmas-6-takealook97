package christmas.view;

import static christmas.exception.ErrorMessage.*;
import static christmas.view.constant.InputConstant.*;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public void printInit() {
		System.out.println(INIT_MSG.getValue());
	}

	public void printDayRequest() {
		System.out.println(DAY_REQUEST.getValue());
	}

	public void printOrderRequest() {
		System.out.println(ORDER_REQUEST.getValue());
	}

	public String getInput() throws IllegalArgumentException {
		String input = Console.readLine();
		if (input.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_ERROR.getMessage());
		}
		return input;
	}
}
