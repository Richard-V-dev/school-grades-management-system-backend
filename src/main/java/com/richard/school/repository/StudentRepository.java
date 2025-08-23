package com.richard.school.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.richard.school.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
	List<Student> findAllByOrderByNameAsc();
}
