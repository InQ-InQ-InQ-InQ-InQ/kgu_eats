package kgueats.domain.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.model.dto.JoinDto;
import kgueats.domain.member.exception.StudentNotFoundException;
import kgueats.domain.member.model.entity.Student;
import kgueats.domain.member.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public Student getStudentEntity(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
	}

	public boolean isStudentExist(Long studentId) {
		return studentRepository.existsById(studentId);
	}

	public void saveStudent(JoinDto joinDto) {
		studentRepository.save(joinDto.toEntity());
	}

}
