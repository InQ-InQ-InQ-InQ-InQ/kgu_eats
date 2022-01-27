package kgueats.domain.store.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.store.model.entity.Menu;

@Getter
public class MenuDto {

	private final Long id;
	private final String name;
	private final Long price;

	@Builder
	@JsonCreator
	public MenuDto(
		@JsonProperty("menuId") Long id,
		@JsonProperty("menuName") String name,
		@JsonProperty("price") Long price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public static MenuDto toDto(Menu menu) {
		return MenuDto.builder()
				.id(menu.getId())
				.name(menu.getName())
				.price(menu.getPrice())
				.build();
	}

}
