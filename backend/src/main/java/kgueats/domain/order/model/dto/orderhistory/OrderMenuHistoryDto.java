package kgueats.domain.order.model.dto.orderhistory;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.entity.OrderMenuHistory;

@Getter
public class OrderMenuHistoryDto {

	private Long id;
	private Long menuId;
	private LocalDateTime orderDate;

	@Builder
	@JsonCreator
	public OrderMenuHistoryDto(
		@JsonProperty("id") Long id,
		@JsonProperty("menuId") Long menuId,
		@JsonProperty("orderDate") LocalDateTime orderDate) {
		this.id = id;
		this.menuId = menuId;
		this.orderDate = orderDate;
	}

	public static OrderMenuHistoryDto toDto(OrderMenuHistory orderMenuHistory) {
		return OrderMenuHistoryDto.builder()
			.id(orderMenuHistory.getId())
			.menuId(orderMenuHistory.getMenu().getId())
			.orderDate(orderMenuHistory.getOrderDate())
			.build();
	}

}
