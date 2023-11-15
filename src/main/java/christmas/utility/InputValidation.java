package christmas.utility;

import static christmas.exception.ErrorMessage.*;
import static christmas.view.constant.InputConstant.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class InputValidation {
	private static final int KEY_IDX = 0, VALUE_IDX = 1, KEY_VALUE_LENGTH = 2;
	private static final String NUMBER_REGEX = "\\d+";

	public static Map<String, Integer> validatePairs(String[] pairs) {
		return Arrays.stream(pairs)
			.map(String::trim)
			.map(pair -> pair.split(MENU_COUNT_SEPARATOR.getValue()))
			.peek(InputValidation::validatePair)
			.collect(Collectors.toMap(
				keyValue -> keyValue[KEY_IDX],
				keyValue -> Integer.parseInt(keyValue[VALUE_IDX]),
				(existingValue, newValue) -> {
					throw new IllegalArgumentException(ORDER_ERROR.getMessage());
				}
			));
	}

	private static void validatePair(String[] keyValue) {
		if (keyValue.length != KEY_VALUE_LENGTH || !keyValue[VALUE_IDX].matches(NUMBER_REGEX)
			|| Integer.parseInt(keyValue[VALUE_IDX]) <= 0) {
			throw new IllegalArgumentException(ORDER_ERROR.getMessage());
		}
	}
}
