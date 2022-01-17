package com.pk.projekt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

  @Query("SELECT n FROM Note n WHERE n.ownerId = ?1")
  List<Note> findByOwner(Long id);

  @Query("SELECT n FROM Note n WHERE n.id = ?1")
  Note findByNoteId(Long id);

}
