package com.richard.school.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Note {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="Subject can't be empty")
	@Column(unique=false,nullable=false)
	private String subject;
	@Column(unique=false,nullable=false)
	@Min(value=0, message="Score can't be lower than zero")
	@Max(value=100, message = "Score can't be higher than 100")
	private Double score;
	@ManyToOne
	@JoinColumn(name="student_id",nullable=false)///////////////
	@JsonBackReference
	private Student student;
	public Integer getId(){return this.id;}
	public String getSubject() {return this.subject;}
	public Double getScore() {return this.score;}
	public Student getStudent() {return this.student;}
	public void setId(Integer id) {this.id = id;}
	public void setSubject(String subject) {this.subject = subject;}
	public void setScore(Double score) {this.score = score;}
	public void setStudent(Student student) {this.student = student;}
}
