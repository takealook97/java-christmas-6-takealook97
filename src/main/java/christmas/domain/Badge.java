package christmas.domain;

import static christmas.domain.constant.BadgeConstant.*;

public class Badge {
	private String badgeName;

	public Badge(long sumBenefit) {
		if (sumBenefit >= SANTA_CLAUS_BADGE.getQualifyingPrice()) {
			this.badgeName = SANTA_CLAUS_BADGE.getBadgeName();
		} else if (sumBenefit >= TREE_BADGE.getQualifyingPrice()) {
			this.badgeName = TREE_BADGE.getBadgeName();
		} else if (sumBenefit >= STAR_BADGE.getQualifyingPrice()) {
			this.badgeName = STAR_BADGE.getBadgeName();
		}
	}

	public String getBadgeName() {
		return badgeName;
	}
}
