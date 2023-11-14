package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BadgeTest {

	@Test
	void badgeName_별() {
		long sumBenefit = 5000;
		Badge badge = new Badge(sumBenefit);
		assertEquals("별", badge.getBadgeName());
	}

	@Test
	void badgeName_트리() {
		long sumBenefit = 10000;
		Badge badge = new Badge(sumBenefit);
		assertEquals("트리", badge.getBadgeName());
	}

	@Test
	void badgeName_산타() {
		long sumBenefit = 25000;
		Badge badge = new Badge(sumBenefit);
		assertEquals("산타", badge.getBadgeName());
	}

	@Test
	void badgeName_Null() {
		long sumBenefit = 1000;
		Badge badge = new Badge(sumBenefit);
		assertNull(badge.getBadgeName());
	}
}
