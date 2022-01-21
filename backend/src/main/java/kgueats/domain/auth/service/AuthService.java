package kgueats.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.config.JwtAuthenticationProvider;
import kgueats.domain.auth.model.dto.JoinDto;
import kgueats.domain.auth.model.dto.LoginDto;
import kgueats.domain.member.exception.PasswordNotMatchException;
import kgueats.domain.member.exception.StudentAlreadyExistException;
import kgueats.domain.member.exception.StudentNotFoundException;
import kgueats.domain.member.model.entity.Student;
import kgueats.domain.member.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtAuthenticationProvider jwtAuthenticationProvider;
	private final PasswordEncoder passwordEncoder;
	private final StudentRepository studentRepository;

	public void join(JoinDto joinDto) {
		if (studentRepository.existsById(joinDto.getId())) {
			throw new StudentAlreadyExistException();
		}
		joinDto.encodePassword(passwordEncoder);
		studentRepository.save(joinDto.toEntity());
	}

	public String login(LoginDto loginDto) {
		Student student = getStudentById(loginDto.getId());
		if (!passwordEncoder.matches(loginDto.getPassword(), student.getPassword())) {
			throw new PasswordNotMatchException();
		}
		return jwtAuthenticationProvider.createToken(String.valueOf(student.getId()), student.getRoles());
	}

	private Student getStudentById(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
	}

}
