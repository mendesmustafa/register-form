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
public class StudentServiceTest {

    private final static String DEFAULT_FIRST_NAME = "ali";
    private final static String DEFAULT_LAST_NAME = "can";
    private final static Long DEFAULT_SCHOOL_NUMBER = 1L;
    private final static String DEFAULT_ADDRESS = "adana";
    private final static String DEFAULT_GENDER = "male";

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
    public void create() {
        defaultResult = studentService.save(defaultModel);
        assertNotNull(defaultResult.getId());
    }

    @Test
    public void delete() {
        defaultResult = studentService.save(defaultModel);
        studentService.delete(defaultResult.getId());
        Student student = studentService.findById(defaultResult.getId());
        assertNull(student);
    }

    @Test
    public void findById() {
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
    public void list() {
        defaultResult = studentService.save(defaultModel);
        assertNotNull(defaultResult);

        StudentModel model = new StudentModel();
        model.setFirstName("ayse");
        model.setLastName("bak");
        model.setSchoolNo(2L);
        model.setAddress("istanbul");
        model.setGender("female");
        StudentModel modelResult = studentService.save(model);
        assertNotNull(modelResult);

        List<Student> list = studentService.list();
        assertEquals(list.size(), 2);
    }
}