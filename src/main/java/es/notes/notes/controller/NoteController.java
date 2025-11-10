package es.notes.notes.controller;

import es.notes.notes.exceptions.TareaNotFoundException;
import es.notes.notes.model.Note;
import es.notes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

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
    public String mostrarNotas(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser) {
        List<Note> allNotes = noteService.findAllFor(authUser.getUsername());
        model.addAttribute("allNotes", allNotes);
        return "notes";
    }

    //muestra el contenido de una nota
    @GetMapping("/content/{id}")
    public String mostrarContenidoNota(@PathVariable Long id, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser){
        Note note = noteService.findByIdFor(id, authUser.getUsername())
                .orElseThrow(() -> new TareaNotFoundException(id));
        model.addAttribute("note", note);
        return "noteContent";
    }

    //formulario para crear nota
    @GetMapping("/newNote")
    public String mostrarFormularioCrearNota(Model model){
        model.addAttribute("note", new Note());
        return "newNote";
    }

    @GetMapping("/update/{id}")
    public String modificarNota(@PathVariable Long id, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser){
        Note note = noteService.findByIdFor(id, authUser.getUsername())
                .orElseThrow(() -> new TareaNotFoundException(id));
        model.addAttribute("note", note);
        return "updateNote";
    }

    //guarda la nota
    @PostMapping("/save")
    public String crearNota(Note note, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser){
        noteService.createFor(note, authUser.getUsername());
        return "redirect:/notes";
    }

    //borra la nota
    @PostMapping("/delete/{id}")
    public String borrarNota(@PathVariable Long id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser){
        try{
            noteService.findByIdFor(id, authUser.getUsername()).ifPresent(aux -> noteService.deleteByIdFor(id, authUser.getUsername()));
        }catch(TareaNotFoundException e){
            throw new TareaNotFoundException(id);
        }finally {
            return "redirect:/notes";
        }

    }

    @PostMapping("/updating/{id}")
    public String modificar(@PathVariable Long id,@ModelAttribute("note") Note note, @AuthenticationPrincipal org.springframework.security.core.userdetails.User authUser){
        noteService.updateByIdFor(id,note,authUser.getUsername());
        return "redirect:/notes";
    }

}
