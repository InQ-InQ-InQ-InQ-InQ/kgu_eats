package kgueats.domain.auth.model.dto;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.member.model.entity.Student;

@Getter
public class JoinDto {

	private Long id;
	private String username;
	private String password;
	private List<String> roles = Collections.singletonList("ROLE_USER");

	@Builder
	@JsonCreator
	public JoinDto(
		@JsonProperty("studentId") Long id,
		@JsonProperty("username") String username,
		@JsonProperty("password") String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(password);
	}

	public Student toEntity() {
		return Student.builder()
			.id(this.id)
			.username(this.username)
			.password(this.password)
			.roles(this.roles)
			.build();
	}

}
