package com.mendes.service;

import com.mendes.model.dto.StudentModel;
import com.mendes.model.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mendesmustafa on 20.01.2021.
 */

@SpringBootTest
class StudentServiceTest {

    private final static String DEFAULT_FIRST_NAME = "TEST-FIRST-NAME";
    private final static String DEFAULT_LAST_NAME = "TEST-LAST-NAME";
    private final static Long DEFAULT_SCHOOL_NUMBER = 1L;
    private final static String DEFAULT_ADDRESS = "TEST-ADDRESS";
    private final static String DEFAULT_GENDER = "TEST-GENDER";

    StudentModel defaultModel;
    StudentModel defaultResult;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        defaultModel = new StudentModel();
        defaultModel.setFirstName(DEFAULT_FIRST_NAME);
        defaultModel.setLastName(DEFAULT_LAST_NAME);
        defaultModel.setSchoolNo(DEFAULT_SCHOOL_NUMBER);
        defaultModel.setAddress(DEFAULT_ADDRESS);
        defaultModel.setGender(DEFAULT_GENDER);
    }

    @AfterEach
    public void clear() {
        if (defaultResult != null && defaultResult.getId() != null) {
            studentService.delete(defaultResult.getId());
        }
    }

    @Test
    void create() {
        defaultResult = studentService.save(defaultModel);
        assertNotNull(defaultResult.getId());
    }

    @Test
    void delete() {
        defaultResult = studentService.save(defaultModel);
        studentService.delete(defaultResult.getId());
        Student student = studentService.findById(defaultResult.getId());
        assertNull(student);
    }

    @Test
    void findById() {
        defaultResult = studentService.save(defaultModel);
        assertNotNull(defaultResult);
        StudentModel model = studentService.getById(defaultResult.getId());
        assertAll(
                () -> assertNotNull(model),
                () -> assertEquals(model.getId(), defaultResult.getId()),
                () -> assertEquals(model.getFirstName(), defaultResult.getFirstName()),
                () -> assertEquals(model.getLastName(), defaultResult.getLastName()),
                () -> assertEquals(model.getAddress(), defaultResult.getAddress()),
                () -> assertEquals(model.getGender(), defaultResult.getGender()),
                () -> assertEquals(model.getSchoolNo(), defaultResult.getSchoolNo())
        );
    }

    @Test
    void list() {
        defaultResult = studentService.save(defaultModel);
        assertNotNull(defaultResult);
        List<StudentModel> list = studentService.list();
        assertEquals(1, list.size());
    }
}