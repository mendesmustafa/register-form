package com.mendes.controller;

import com.mendes.model.dto.StudentModel;
import com.mendes.model.entity.Student;
import com.mendes.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mendesmustafa on 20.12.2020.
 */

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("studentModel") StudentModel studentModel) {
        studentService.save(studentModel);
        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model) {
        List<Student> students = studentService.list();
        model.addAttribute("students", students);
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        studentService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("studentModel", studentService.getById(id));
        return "newStudent";
    }

    @GetMapping("/addStudentForm")
    public String add(Model model) {
        StudentModel studentModel = new StudentModel();
        model.addAttribute("studentModel", studentModel);
        return "newStudent";
    }
}
