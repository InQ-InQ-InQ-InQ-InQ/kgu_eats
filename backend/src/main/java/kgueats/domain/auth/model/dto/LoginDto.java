package kgueats.domain.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginDto {

	private Long id;
	private String password;

	@Builder
	@JsonCreator
	public LoginDto(
		@JsonProperty("studentId") Long id,
		@JsonProperty("password") String password) {
		this.id = id;
		this.password = password;
	}

}
