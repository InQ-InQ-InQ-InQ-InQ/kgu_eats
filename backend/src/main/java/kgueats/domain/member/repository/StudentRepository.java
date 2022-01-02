package kgueats.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.member.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
