package kgueats.entity.store;

public enum CampusEnum {

	SUWON("수원캠퍼스", 1),
	SEOUL("서울캠퍼스", 2);

	private final String name;
	private final int index;

	CampusEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

}
