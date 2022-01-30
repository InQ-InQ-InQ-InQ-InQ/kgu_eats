package kgueats.domain.review.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewPostDto {

	private Long menuId;
	private String content;

	@Builder
	@JsonCreator
	public ReviewPostDto(
		@JsonProperty("menuId") Long menuId,
		@JsonProperty("content") String content) {
		this.menuId = menuId;
		this.content = content;
	}

}
