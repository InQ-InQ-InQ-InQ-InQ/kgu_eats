package kgueats.domain.store.model.dto;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.store.model.entity.CampusEnum;
import kgueats.domain.store.model.entity.Store;

@Getter
public class StoreSimpleDto {

	private final Long id;
	private final Long campusId;
	private final String name;

	@Builder
	public StoreSimpleDto(Long id, CampusEnum campus, String name) {
		this.id = id;
		this.campusId = campus.getId();
		this.name = name;
	}

	public static StoreSimpleDto toDto(Store store) {
		return StoreSimpleDto.builder()
				.id(store.getId())
				.campus(store.getCampus())
				.name(store.getName())
				.build();
	}

}
