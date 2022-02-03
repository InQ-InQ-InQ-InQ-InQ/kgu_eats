package kgueats.domain.store.model.entity;

public enum CampusEnum {

	SUWON(1L, "수원캠퍼스"),
	SEOUL(2L, "서울캠퍼스");

	private final Long id;
	private final String name;

	CampusEnum(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

}
