package kgueats.domain.review.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.dto.orderhistory.OrderMenuHistoryDto;
import kgueats.domain.order.model.entity.OrderMenuHistory;
import kgueats.domain.review.model.entity.Review;

@Getter
public class ReviewGetDto {

	private Long reviewId;
	private Long studentId;
	private OrderMenuHistoryDto orderMenuHistoryDto;
	private String content;
	private LocalDateTime writtenDateTime;
	private Boolean updated;

	@Builder
	@JsonCreator
	public ReviewGetDto(
		@JsonProperty("reviewId") Long reviewId,
		@JsonProperty("studentId") Long studentId,
		@JsonProperty("orderMenuHistoryDto")OrderMenuHistory orderMenuHistory,
		@JsonProperty("content") String content,
		@JsonProperty("writtenDateTime") LocalDateTime writtenDateTime,
		@JsonProperty("isUpdated") Boolean updated) {
		this.reviewId = reviewId;
		this.studentId = studentId;
		this.orderMenuHistoryDto = OrderMenuHistoryDto.toDto(orderMenuHistory);
		this.content = content;
		this.writtenDateTime = writtenDateTime;
		this.updated = updated;
	}

	public static ReviewGetDto toDto(Review review) {
		return ReviewGetDto.builder()
			.reviewId(review.getId())
			.studentId(review.getStudent().getId())
			.orderMenuHistory(review.getOrderMenuHistory())
			.content(review.getContent())
			.writtenDateTime(review.getWrittenDateTime())
			.updated(review.updated)
			.build();
	}

}
