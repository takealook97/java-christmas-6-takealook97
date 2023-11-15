package christmas.domain;

import static christmas.constant.EventYearAndMonth.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class VisitDateTest {
	@Test
	public void 날짜_생성_정상_테스트() {
		int day = 15;
		VisitDate visitDate = new VisitDate(day);
		assertNotNull(visitDate);
	}

	@Test
	public void 범위_밖_날짜_테스트() {
		int day = 32;
		assertThrows(IllegalArgumentException.class, () -> new VisitDate(day));
	}

	@Test
	public void 날짜_정보_획득_테스트() {
		int day = 15;
		VisitDate visitDate = new VisitDate(day);
		assertEquals(LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), day), visitDate.getDate());
	}

	@Test
	public void 요일_획득_테스트() {
		int day = 15; // 테스트할 날짜
		VisitDate visitDate = new VisitDate(day);
		DayOfWeek expectedDayOfWeek = LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), day).getDayOfWeek();
		assertEquals(expectedDayOfWeek, visitDate.getDayOfTheWeek());
	}

}