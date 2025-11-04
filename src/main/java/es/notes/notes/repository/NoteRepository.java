package es.notes.notes.repository;

import es.notes.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    //Listar solo las notas del usuario
    List<Note> findByOwnerUsername(String username);

    //Acceder a una nota SOLO si pertenece al usuario
    Optional<Note> findByIdAndOwnerUsername(Long id, String username);
}
