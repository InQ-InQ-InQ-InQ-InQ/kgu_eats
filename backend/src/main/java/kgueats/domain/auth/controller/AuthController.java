package kgueats.domain.auth.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.model.dto.JoinDto;
import kgueats.domain.auth.model.dto.LoginDto;
import kgueats.domain.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping(value = "/join")
	public String join(@RequestBody JoinDto joinDto) {
		authService.join(joinDto);
		return "join";
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
		String token = authService.login(loginDto);
		httpServletResponse.setHeader("X-AUTH-TOKEN", token);
		return "login";
	}

}
