package kgueats.domain.order.model.dto.orderform.menu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderMenuDto {

	private Long storeId;
	private Long menuId;

	@Builder
	@JsonCreator
	public OrderMenuDto(
		@JsonProperty("storeId") Long storeId,
		@JsonProperty("menuId") Long menuId) {
		this.storeId = storeId;
		this.menuId = menuId;
	}

}
