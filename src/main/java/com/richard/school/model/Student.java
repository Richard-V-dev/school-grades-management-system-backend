package com.richard.school.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true, nullable=false)
	@NotBlank(message="Email can't be empty")
	private String name;
	@Column(unique=true, nullable=false)
	@NotBlank(message="Email can't be empty")
	@Email(message="Emai must be a valid one")
	private String email;
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonManagedReference
	private List<Note> notes;
	public Integer getId() {return this.id;}
	public void setId(Integer id) {this.id = id;}
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	public String getEmail() {return this.email;}
	public void setEmail(String email) {this.email=email;}
	public List<Note> getNotes(){return this.notes;}
	public void setNotes(List<Note> notes) {this.notes = notes;}
}
