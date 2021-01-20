package com.mendes.service;

import com.mendes.model.dto.StudentModel;
import com.mendes.model.entity.Student;
import com.mendes.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by mendesmustafa on 20.12.2020.
 */

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(Long id) {
        Student student = null;
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            student = studentOptional.get();
        }
        return student;
    }

    public StudentModel save(StudentModel model) {
        Student student = new Student();
        if (model.getId() != null) {
            Optional<Student> studentOptional = studentRepository.findById(model.getId());
            if (studentOptional.isPresent()) {
                student = studentOptional.get();
            }
        }
        fill(student, model);
        studentRepository.save(student);
        return fillModel(student);
    }

    private void fill(Student student, StudentModel model) {
        student.setFirstName(model.getFirstName());
        student.setLastName(model.getLastName());
        student.setSchoolNo(model.getSchoolNo());
        student.setAddress(model.getAddress());
        student.setGender(model.getGender());
    }

    private StudentModel fillModel(Student student) {
        StudentModel model = new StudentModel();
        model.setId(student.getId());
        model.setFirstName(student.getFirstName());
        model.setLastName(student.getLastName());
        model.setSchoolNo(student.getSchoolNo());
        model.setAddress(student.getAddress());
        model.setGender(student.getGender());
        return model;
    }

    public StudentModel getById(Long id) {
        StudentModel model = null;
        Student student = findById(id);
        if (student != null) {
            model = fillModel(student);
        }
        return model;
    }

    public void delete(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            System.out.println("Student deleted " + id);
        } else {
            System.out.println("Student did't found " + id);
        }
    }

    public List<Student> list() {
        List<Student> students = studentRepository.findAll();
        return students;
    }
}
