package kgueats.domain.review.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewPostDto {

	private Long orderMenuHistoryId;
	private String content;

	@Builder
	@JsonCreator
	public ReviewPostDto(
		@JsonProperty("orderMenuHistoryId") Long orderMenuHistoryId,
		@JsonProperty("content") String content) {
		this.orderMenuHistoryId = orderMenuHistoryId;
		this.content = content;
	}

}
