package kgueats.domain.order.model.dto.orderhistory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.entity.OrderTicketHistory;
import kgueats.domain.order.model.entity.OrderTicketHistoryUnit;

@Getter
public class OrderTicketHistoryDto {

	private Long id;
	private LocalDateTime orderDate;
	private List<OrderTicketHistoryUnitDto> orderTicketHistoryUnitDtos;

	@Builder
	@JsonCreator
	public OrderTicketHistoryDto(
		@JsonProperty("id") Long id,
		@JsonProperty("orderDate") LocalDateTime orderDate,
		@JsonProperty("historyUnits") List<OrderTicketHistoryUnit> orderHistoryUnits) {
		this.id = id;
		this.orderDate = orderDate;
		this.orderTicketHistoryUnitDtos = orderHistoryUnits.stream()
			.map(OrderTicketHistoryUnitDto::toDto).collect(Collectors.toList());
	}

	public static OrderTicketHistoryDto toDto(OrderTicketHistory orderTicketHistory) {
		return OrderTicketHistoryDto.builder()
			.id(orderTicketHistory.getId())
			.orderDate(orderTicketHistory.getOrderDate())
			.orderHistoryUnits(orderTicketHistory.getOrderTicketHistoryUnits())
			.build();
	}

}
