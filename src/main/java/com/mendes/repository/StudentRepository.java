package com.mendes.repository;

import com.mendes.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mendesmustafa on 20.12.2020.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}