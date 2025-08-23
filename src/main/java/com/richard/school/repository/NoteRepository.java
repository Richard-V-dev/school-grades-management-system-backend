package com.richard.school.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.richard.school.model.Note;
public interface NoteRepository extends JpaRepository<Note,Integer>{

}
