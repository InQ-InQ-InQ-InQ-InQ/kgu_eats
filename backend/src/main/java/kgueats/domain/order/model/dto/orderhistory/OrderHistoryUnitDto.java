package kgueats.domain.order.model.dto.orderhistory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.entity.OrderHistoryUnit;

@Getter
public class OrderHistoryUnitDto {

	private Long menuId;
	private Long amount;

	@Builder
	@JsonCreator
	public OrderHistoryUnitDto(
		@JsonProperty("menuId") Long menuId,
		@JsonProperty("amount") Long amount) {
		this.menuId = menuId;
		this.amount = amount;
	}

	public static OrderHistoryUnitDto toDto(OrderHistoryUnit orderHistoryUnit) {
		return OrderHistoryUnitDto.builder()
			.menuId(orderHistoryUnit.getMenu().getId())
			.amount(orderHistoryUnit.getAmount())
			.build();
	}

}
