package com.statestreet.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statestreet.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public Optional<Student> findStudentByStudentId(Long id);
}
