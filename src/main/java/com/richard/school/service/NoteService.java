package com.richard.school.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.richard.school.model.Note;
import com.richard.school.model.Student;
import com.richard.school.repository.NoteRepository;
import com.richard.school.repository.StudentRepository;
@Service
public class NoteService {
	private StudentRepository studentRepository;
	private NoteRepository noteRepository;
	public NoteService(StudentRepository studentRepository, NoteRepository noteRepository) {
		this.studentRepository = studentRepository;
		this.noteRepository = noteRepository;
	}
	public List<Note> getAllNotes() {
		return this.noteRepository.findAll();
	}
	public Optional<Note> getNoteById(Integer id){
		return this.noteRepository.findById(id);
	}
	public void deleteNote(Integer id) {
		this.noteRepository.deleteById(id);
	}
	public Note saveNoteForStudent(Integer id,Note note) {
		Student student = this.studentRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Student not found"));
		note.setStudent(student);
		return this.noteRepository.save(note);
	}/*
	public Note saveNote(Note note) {
		return this.noteRepository.save(note);
	}*/
	public Double calculateAverage(Integer studentId) {
		Student student = this.studentRepository.findById(studentId)
				.orElseThrow(()->new RuntimeException("Student not found"));
		List<Note> notes = student.getNotes();
		if (notes.isEmpty()) return 0.0;
		double sum = notes.stream().mapToDouble(note->note.getScore()).sum();
		return sum/notes.size();
	}
}
