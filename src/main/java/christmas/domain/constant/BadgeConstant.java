package christmas.domain.constant;

public enum BadgeConstant {
	STAR_BADGE("별", 5000),
	TREE_BADGE("트리", 10000),
	SANTA_CLAUS_BADGE("산타", 20000);

	private final String badgeName;
	private final long qualifyingPrice;

	BadgeConstant(String badgeName, long qualifyingPrice) {
		this.badgeName = badgeName;
		this.qualifyingPrice = qualifyingPrice;
	}

	public String getBadgeName() {
		return badgeName;
	}

	public long getQualifyingPrice() {
		return qualifyingPrice;
	}
}
