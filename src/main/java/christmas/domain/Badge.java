package christmas.domain;

import static christmas.domain.constant.BadgeConstant.*;

public class Badge {
	private String name;

	public Badge(long sumBenefit) {
		if (sumBenefit >= SANTA_CLAUS_BADGE.getQualifyingPrice()) {
			this.name = SANTA_CLAUS_BADGE.getBadgeName();
		} else if (sumBenefit >= TREE_BADGE.getQualifyingPrice()) {
			this.name = TREE_BADGE.getBadgeName();
		} else if (sumBenefit >= STAR_BADGE.getQualifyingPrice()) {
			this.name = STAR_BADGE.getBadgeName();
		}
	}

	public String getName() {
		return name;
	}
}
