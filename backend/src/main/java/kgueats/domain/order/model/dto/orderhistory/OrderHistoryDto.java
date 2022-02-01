package kgueats.domain.order.model.dto.orderhistory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.entity.OrderHistory;
import kgueats.domain.order.model.entity.OrderHistoryUnit;

@Getter
public class OrderHistoryDto {

	private Long id;
	private Long storeId;
	private LocalDateTime orderDate;
	private List<OrderHistoryUnitDto> orderHistoryUnitDtos;

	@Builder
	@JsonCreator
	public OrderHistoryDto(
		@JsonProperty("id") Long id,
		@JsonProperty("storeId") Long storeId,
		@JsonProperty("orderDate") LocalDateTime orderDate,
		@JsonProperty("orderHistoryUnits") List<OrderHistoryUnit> orderHistoryUnits) {
		this.id = id;
		this.storeId = storeId;
		this.orderDate = orderDate;
		this.orderHistoryUnitDtos = orderHistoryUnits.stream()
			.map(OrderHistoryUnitDto::toDto).collect(Collectors.toList());
	}

	public static OrderHistoryDto toDto(OrderHistory orderHistory) {
		return OrderHistoryDto.builder()
			.id(orderHistory.getId())
			.storeId(orderHistory.getStore().getId())
			.orderDate(orderHistory.getOrderDate())
			.orderHistoryUnits(orderHistory.getOrderHistoryUnits())
			.build();
	}

}
