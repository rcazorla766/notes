package es.notes.notes.controller;

import es.notes.notes.model.Note;
import es.notes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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
    public String mostrarNotas(Model model) {
        List<Note> allNotes = noteService.obtenerTodas();
        model.addAttribute("allNotes", allNotes);
        return "notes";
    }

    @GetMapping("/newNote")
    public String mostrarFormularioCrearNota(Model model){
        model.addAttribute("note", new Note());
        return "newNote";
    }

    @PostMapping("/save")
    public String crearNota(Note note){
        noteService.crearNota(note);
        return "redirect:/notes";
    }



    //TODO
}
