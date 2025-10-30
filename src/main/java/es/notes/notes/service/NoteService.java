package es.notes.notes.service;

import es.notes.notes.model.Note;
import es.notes.notes.repository.NoteRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository){
        this.noteRepository=noteRepository;
    }

    public Note crearNota(Note note){
        return noteRepository.save(note);
    }

    public Optional<Note> obtenerPorId(Long id){
        return noteRepository.findById(id);
    }

    public List<Note> obtenerTodas(){
        return noteRepository.findAll();
    }

    public Note actualizarPorId(Long id, Note nuevo){
        return noteRepository.findById(id).map(n -> {
            // Actualiza los campos necesarios
            n.setTitle(nuevo.getTitle());
            n.setContent(nuevo.getContent());
            // Guarda los cambios
            return noteRepository.save(n);
        }).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
    }

    public void borrarNota(Note note) {
        noteRepository.delete(note);
    }

    public void borrarPorId(Long id) {
        noteRepository.deleteById(id);
    }
}
