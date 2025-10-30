package es.notes.notes.controller;

import es.notes.notes.model.Note;
import es.notes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Página principal de notas
    @GetMapping
    public String mostrarNotas() {
        // Busca src/main/resources/templates/notes.html
        return "notes";
    }

    @GetMapping("/newNote")
    public String crearNota(){
        return "newNote";
    }



    //TODO
}
