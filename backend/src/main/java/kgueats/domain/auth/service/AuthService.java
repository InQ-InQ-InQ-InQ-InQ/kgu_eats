package kgueats.domain.auth.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.config.JwtAuthenticationProvider;
import kgueats.domain.auth.model.dto.JoinDto;
import kgueats.domain.auth.model.dto.LoginDto;
import kgueats.domain.member.exception.PasswordNotMatchException;
import kgueats.domain.member.exception.StudentAlreadyExistException;
import kgueats.domain.member.model.entity.Student;
import kgueats.domain.member.service.StudentService;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtAuthenticationProvider jwtAuthenticationProvider;
	private final PasswordEncoder passwordEncoder;

	private final StudentService studentService;

	public void join(JoinDto joinDto) {
		if (studentService.isStudentExist(joinDto.getId())) {
			throw new StudentAlreadyExistException();
		}
		joinDto.encodePassword(passwordEncoder);
		studentService.saveStudent(joinDto);
	}

	public String login(LoginDto loginDto) {
		Student student = getStudentById(loginDto.getId());
		if (!passwordEncoder.matches(loginDto.getPassword(), student.getPassword())) {
			throw new PasswordNotMatchException();
		}
		return jwtAuthenticationProvider.createToken(String.valueOf(student.getId()), student.getRoles());
	}

	private Student getStudentById(Long studentId) {
		return studentService.getStudentEntity(studentId);
	}

	public Student getAuthStudent() {
		return getStudentById(getAuthStudentId());
	}

	private Long getAuthStudentId() {
		Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (details != null && !(details instanceof String))
			return ((Student) details).getId();
		return null;
	}

}
