package kgueats.domain.review.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewPatchDto {

	private String content;

	@Builder
	@JsonCreator
	public ReviewPatchDto(
		@JsonProperty("content") String content) {
		this.content = content;
	}

}
