package com.richard.school.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.richard.school.model.Student;
import com.richard.school.service.StudentService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {
	StudentService studentService;
	public StudentController(StudentService studentService){
		this.studentService = studentService;
	}
	@GetMapping
	public List<Student> getAllStudents(){
		return this.studentService.getAllStudents();
	}
	@GetMapping("/{id}")
	public Optional<Student> getStudent(@PathVariable Integer id){
		return this.studentService.getStudentById(id);
	}
	@PostMapping
	public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
		Student savedStudent = this.studentService.saveStudent(student);
		return ResponseEntity.ok(savedStudent);
	}
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		this.studentService.deleteStudent(id);
	}
	@PutMapping("/{id}")
	public Student editStudent(@PathVariable Integer id,@RequestBody Student updatedStudent) {
		return this.studentService.getStudentById(id).map(student->{
			student.setName(updatedStudent.getName());
			student.setEmail(updatedStudent.getEmail());
			return this.studentService.saveStudent(student);
		}).orElseThrow(()->new RuntimeException("Studend not found)"));
	}
}	
