package christmas.utility;

import static christmas.exception.ErrorMessage.*;
import static christmas.view.constant.InputConstant.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class InputValidation {
	public static Map<String, Integer> validatePairs(String[] pairs) throws IllegalArgumentException {
		return Arrays.stream(pairs)
			.map(pair -> {
				String[] keyValue = pair.split(MENU_COUNT_SEPARATOR.getValue());
				if (keyValue.length != 2 || !keyValue[1].matches("\\d+")) {
					throw new IllegalArgumentException(ORDER_ERROR.getMessage());
				}
				return keyValue;
			})
			.collect(Collectors.toMap(
				keyValue -> keyValue[0],
				keyValue -> parseCount(keyValue[1])
			));
	}

	private static int parseCount(String countStr) throws IllegalArgumentException {
		int count = Integer.parseInt(countStr);
		if (count <= 0) {
			throw new IllegalArgumentException(ORDER_ERROR.getMessage());
		}
		return count;
	}
}
