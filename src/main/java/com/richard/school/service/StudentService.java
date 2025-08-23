package com.richard.school.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.richard.school.model.Student;
import com.richard.school.repository.StudentRepository;
@Service
public class StudentService {
	private final StudentRepository studentRepository;
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	public List<Student> getAllStudents(){
		return this.studentRepository.findAllByOrderByNameAsc(); 
	}
	public Optional<Student> getStudentById(Integer id) {
		return this.studentRepository.findById(id);
	}
	public Student saveStudent(Student student) {
		return this.studentRepository.save(student);
	}
	public void deleteStudent(Integer id) {
		this.studentRepository.deleteById(id);
	}
}
