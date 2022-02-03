package kgueats.domain.store.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonCreator
	public StoreSimpleDto(
		@JsonProperty("storeId") Long id,
		@JsonProperty("campusId") CampusEnum campus,
		@JsonProperty("name") String name) {
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
