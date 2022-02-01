package kgueats.domain.order.model.dto.orderform.ticket;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderTicketDto {

	private Long storeId;
	private List<OrderTicketUnitDto> orderTicketUnitDtos;

	@Builder
	@JsonCreator
	public OrderTicketDto(
		@JsonProperty("storeId") Long storeId,
		@JsonProperty("orderUnits") List<OrderTicketUnitDto> orderTicketUnitDtos) {
		this.storeId = storeId;
		this.orderTicketUnitDtos = orderTicketUnitDtos;
	}

}
