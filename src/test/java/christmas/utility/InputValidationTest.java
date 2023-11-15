package christmas.utility;

import static christmas.exception.ErrorMessage.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

class InputValidationTest {

	@Test
	void 키_밸류_유효한_입력() {
		String[] validInput = {"양송이수프-2", "타파스-3"};
		Map<String, Integer> result = InputValidation.validatePairs(validInput);

		assertEquals(2, result.size());
		assertTrue(result.containsKey("양송이수프"));
		assertTrue(result.containsKey("타파스"));
		assertEquals(2, result.get("양송이수프"));
		assertEquals(3, result.get("타파스"));
	}

	@Test
	void 유효하지않은_메뉴이름() {
		String[] invalidInput = {"InvalidMenu:2"};

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			InputValidation.validatePairs(invalidInput);
		});

		assertEquals(ORDER_ERROR.getMessage(), exception.getMessage());
	}

	@Test
	void 유효하지않은_입력형식() {
		String[] invalidInput = {"양송이수프,2", "타파스:three"};

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			InputValidation.validatePairs(invalidInput);
		});

		assertEquals(ORDER_ERROR.getMessage(), exception.getMessage());
	}

	@Test
	void 범위를벗어난_주문메뉴개수() {
		String[] invalidInput = {"양송이수프:-1", "타파스:0"};

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			InputValidation.validatePairs(invalidInput);
		});

		assertEquals(ORDER_ERROR.getMessage(), exception.getMessage());
	}
}
