package kgueats.domain.review.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.dto.orderhistory.OrderMenuHistoryDto;
import kgueats.domain.order.model.entity.OrderMenuHistory;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.review.model.entity.ReviewImage;

@Getter
public class ReviewGetDto {

	private Long reviewId;
	private Long studentId;
	private OrderMenuHistoryDto orderMenuHistoryDto;
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime writtenDateTime;
	private Boolean updated;
	private List<ReviewImageDto> reviewImageDtos;

	@Builder
	@JsonCreator
	public ReviewGetDto(
		@JsonProperty("reviewId") Long reviewId,
		@JsonProperty("studentId") Long studentId,
		@JsonProperty("orderMenuHistoryDto")OrderMenuHistory orderMenuHistory,
		@JsonProperty("content") String content,
		@JsonProperty("writtenDateTime") LocalDateTime writtenDateTime,
		@JsonProperty("isUpdated") Boolean updated,
		@JsonProperty("images")List<ReviewImage> reviewImages) {
		this.reviewId = reviewId;
		this.studentId = studentId;
		this.orderMenuHistoryDto = OrderMenuHistoryDto.toDto(orderMenuHistory);
		this.content = content;
		this.writtenDateTime = writtenDateTime;
		this.updated = updated;
		this.reviewImageDtos = reviewImages.stream().map(ReviewImageDto::toDto).collect(Collectors.toList());
	}

	public static ReviewGetDto toDto(Review review) {
		return ReviewGetDto.builder()
			.reviewId(review.getId())
			.studentId(review.getStudent().getId())
			.orderMenuHistory(review.getOrderMenuHistory())
			.content(review.getContent())
			.writtenDateTime(review.getWrittenDateTime())
			.updated(review.updated)
			.reviewImages(review.getReviewImages())
			.build();
	}

}
