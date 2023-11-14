package christmas.dto;

public record BenefitInfo(boolean isDDayOrBefore, boolean isWeekday,
						  boolean isSpecialDay, boolean isQualifiedForGift) {
}
