package kgueats.domain.store.model.dto;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.store.model.entity.Menu;

@Getter
public class MenuDto {

	private final Long id;
	private final String name;
	private final Long price;

	@Builder
	public MenuDto(Long id, String name, Long price) {
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
