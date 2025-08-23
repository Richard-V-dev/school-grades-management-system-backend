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
import com.richard.school.model.Note;
import com.richard.school.service.NoteService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class NoteController {
	NoteService noteService;
	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}
	@GetMapping
	public List<Note> getAllNotes(){
		return this.noteService.getAllNotes();
	}
	@GetMapping("/{id}")
	public Optional<Note> getNote(@PathVariable Integer id){
		return this.noteService.getNoteById(id);
	}
	@PostMapping("/student/{studentId}")
	public ResponseEntity<Note> saveNoteForStudent(
			@PathVariable Integer studentId,
			@Valid @RequestBody Note note) {
		Note savedNote = this.noteService.saveNoteForStudent(studentId, note);
		return ResponseEntity.ok(savedNote);
	}
	@DeleteMapping("/{id}")
	public void deleteNote(@PathVariable Integer id) {
		this.noteService.deleteNote(id);
	}
	@GetMapping("/student/{studentId}/average")
	public Double calculateAverage(@PathVariable Integer studentId) {
		return this.noteService.calculateAverage(studentId); 
	}	
	@PutMapping("/{id}/student/{studentId}")
	public Note editNote(@PathVariable Integer id,
			@PathVariable Integer studentId,
			@RequestBody Note updatedNote) {
		return this.noteService.getNoteById(id).map(note->{
			note.setScore(updatedNote.getScore());
			note.setSubject(updatedNote.getSubject());
			return this.noteService.saveNoteForStudent(studentId,note);
		}).orElseThrow(()->new RuntimeException("Note not found"));
	}
}
