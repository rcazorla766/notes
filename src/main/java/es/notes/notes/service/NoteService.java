package es.notes.notes.service;

import es.notes.notes.model.Note;
import es.notes.notes.repository.NoteRepository;

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


    //TODO
}
