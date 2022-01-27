package kgueats.domain.order.model.dto.orderform;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDto {

	private Long storeId;
	private List<OrderUnitDto> orderUnitDtos;

	@Builder
	@JsonCreator
	public OrderDto(
		@JsonProperty("storeId") Long storeId,
		@JsonProperty("orderUnits") List<OrderUnitDto> orderUnitDtos) {
		this.storeId = storeId;
		this.orderUnitDtos = orderUnitDtos;
	}

}
