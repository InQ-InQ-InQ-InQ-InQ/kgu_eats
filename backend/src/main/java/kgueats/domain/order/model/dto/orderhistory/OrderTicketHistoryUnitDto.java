package kgueats.domain.order.model.dto.orderhistory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.entity.OrderTicketHistoryUnit;

@Getter
public class OrderTicketHistoryUnitDto {

	private Long ticketId;
	private Long amount;

	@Builder
	@JsonCreator
	public OrderTicketHistoryUnitDto(
		@JsonProperty("ticketId") Long ticketId,
		@JsonProperty("amount") Long amount) {
		this.ticketId = ticketId;
		this.amount = amount;
	}

	public static OrderTicketHistoryUnitDto toDto(OrderTicketHistoryUnit orderTicketHistoryUnit) {
		return OrderTicketHistoryUnitDto.builder()
			.ticketId(orderTicketHistoryUnit.getTicket().getId())
			.amount(orderTicketHistoryUnit.getAmount())
			.build();
	}

}
