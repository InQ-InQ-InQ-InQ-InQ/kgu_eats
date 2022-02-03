package kgueats.domain.store.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.store.model.entity.BusinessHour;
import kgueats.domain.store.model.entity.CampusEnum;
import kgueats.domain.store.model.entity.Store;

@Getter
public class StoreDetailDto {

	private final Long id;
	private final List<BusinessHourDto> businessHours;
	private final Long campusId;
	private final String name;
	private final String location;

	@Builder
	@JsonCreator
	public StoreDetailDto(
		@JsonProperty("storeId") Long id,
		@JsonProperty("businessHours") List<BusinessHour> businessHours,
		@JsonProperty("campusId") CampusEnum campus,
		@JsonProperty("name") String name,
		@JsonProperty("location") String location) {
		this.id = id;
		this.businessHours = businessHours.stream().map(BusinessHourDto::toDto).collect(Collectors.toList());
		this.campusId = campus.getId();
		this.name = name;
		this.location = location;
	}

	public static StoreDetailDto toDto(Store store) {
		return StoreDetailDto.builder()
				.id(store.getId())
				.businessHours(store.getBusinessHours())
				.campus(store.getCampus())
				.name(store.getName())
				.location(store.getLocation())
				.build();
	}

}
