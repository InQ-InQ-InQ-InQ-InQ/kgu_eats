package kgueats.domain.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.exception.StudentNotFoundException;
import kgueats.domain.member.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final StudentRepository studentRepository;

	public UserDetails loadUserByUsername(String studentId) {
		return studentRepository.findById(Long.valueOf(studentId)).orElseThrow(StudentNotFoundException::new);
	}

}
