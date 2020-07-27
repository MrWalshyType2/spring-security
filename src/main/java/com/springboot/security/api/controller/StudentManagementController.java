package com.springboot.security.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.api.student.Student;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	
	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "Bob Daley"),
			new Student(2, "Fred Daley"),
			new Student(3, "Lady Bella")
	);
	
	@GetMapping
	public List<Student> getAllStudents() {
		System.out.println("Get api");
		return STUDENTS;
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println("Register api");
		System.out.println(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable Integer studentId) {
		System.out.println("Delete api");
		System.out.println(studentId);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
		System.out.println("Update api");
		System.out.println(String.format("%s %s", studentId, student));
	}
}
