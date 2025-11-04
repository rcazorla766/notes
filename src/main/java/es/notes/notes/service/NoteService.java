package es.notes.notes.service;

import es.notes.notes.model.AppUser;
import es.notes.notes.model.Note;
import es.notes.notes.repository.AppUserRepository;
import es.notes.notes.repository.NoteRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final AppUserRepository userRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, AppUserRepository userRepository){
        this.noteRepository=noteRepository;
        this.userRepository=userRepository;
    }

    public Note createFor(Note note, String username){
        AppUser owner = userRepository.findByUsername(username).orElseThrow();
        note.setOwner(owner);
        return noteRepository.save(note);
    }

    public Optional<Note> findByIdFor(Long id, String username){
        return noteRepository.findByIdAndOwnerUsername(id, username);
    }

    public List<Note> findAllFor(String username){
        return noteRepository.findByOwnerUsername(username);
    }

    public Note updateByIdFor(Long id, Note nuevo, String username){
        Note n = noteRepository.findByIdAndOwnerUsername(id,username).orElseThrow();
        n.setTitle(nuevo.getTitle());
        n.setContent(nuevo.getContent());
        return noteRepository.save(n);
    }

    public void deleteByIdFor(Long id, String username) {
        Note n =noteRepository.findByIdAndOwnerUsername(id,username).orElseThrow();
        noteRepository.delete(n);
    }
    /*
    public Note createFor(Note note){
        return noteRepository.save(note);
    }

    public Optional<Note> findByIdFor(Long id, String username){
        return noteRepository.findById(id);
    }

    public List<Note> findAllFor(String username){
        return noteRepository.findAll();
    }

    public Note updateByIdFor(Long id, Note nuevo){
        return noteRepository.findById(id).map(n -> {
            // Actualiza los campos necesarios
            n.setTitle(nuevo.getTitle());
            n.setContent(nuevo.getContent());
            // Guarda los cambios
            return noteRepository.save(n);
        }).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
    }

    public void deleteByNoteFor(Note note) {
        noteRepository.delete(note);
    }

    public void deleteByIdFor(Long id) {
        noteRepository.deleteById(id);
    }
    */
}
