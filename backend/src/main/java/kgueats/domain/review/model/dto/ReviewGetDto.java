package kgueats.domain.review.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.review.model.entity.Review;
import kgueats.domain.store.model.dto.MenuDto;
import kgueats.domain.store.model.entity.Menu;

@Getter
public class ReviewGetDto {

	private Long reviewId;
	private Long studentId;
	private MenuDto menuDto;
	private String content;
	private LocalDateTime writtenDateTime;
	private Boolean updated;

	@Builder
	@JsonCreator
	public ReviewGetDto(
		@JsonProperty("reviewId") Long reviewId,
		@JsonProperty("studentId") Long studentId,
		@JsonProperty("menu") Menu menu,
		@JsonProperty("content") String content,
		@JsonProperty("writtenDateTime") LocalDateTime writtenDateTime,
		@JsonProperty("isUpdated") Boolean updated) {
		this.reviewId = reviewId;
		this.studentId = studentId;
		this.menuDto = MenuDto.toDto(menu);
		this.content = content;
		this.writtenDateTime = writtenDateTime;
		this.updated = updated;
	}

	public static ReviewGetDto toDto(Review review) {
		return ReviewGetDto.builder()
			.reviewId(review.getId())
			.studentId(review.getStudent().getId())
			.menu(review.getMenu())
			.content(review.getContent())
			.writtenDateTime(review.getWrittenDateTime())
			.updated(review.updated)
			.build();
	}

}
