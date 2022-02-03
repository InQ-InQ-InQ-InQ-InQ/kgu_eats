package kgueats.domain.review.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.review.model.entity.ReviewImage;

@Getter
public class ReviewImageDto {

	private Long id;
	private String filePath;

	@Builder
	@JsonCreator
	public ReviewImageDto(
		@JsonProperty("imageId") Long id,
		@JsonProperty("imageSrc") String filePath) {
		this.id = id;
		this.filePath = filePath;
	}

	public static ReviewImageDto toDto(ReviewImage reviewImage) {
		return ReviewImageDto.builder()
			.id(reviewImage.getId())
			.filePath(reviewImage.getFilePath())
			.build();
	}

}
