package es.notes.notes.service;

import es.notes.notes.model.AppUser;
import es.notes.notes.model.Note;
import es.notes.notes.repository.AppUserRepository;
import es.notes.notes.repository.NoteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;

    @Mock
    private AppUserRepository userRepository;

    @InjectMocks
    private NoteService noteService;

    private AppUser user;
    private Note note;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        user=new AppUser();
        user.setId(1L);
        user.setUsername("user1");
        user.setPassword("password1");
        user.setRole("ROLE_USER");

        note = new Note();
        note.setId(1L);
        note.setTitle("Title");
        note.setContent("Content");
        note.setOwner(user);
    }

    @Test
    void CreateNote(){

    }

    @Test
    void showAllNotesFromUser(){
        List<Note> noteSimulation = Arrays.asList(note);
        when(noteRepository.findAll()).thenReturn(noteSimulation);

        List<Note> result = noteService.findAllFor(user.getUsername());
        assertEquals(1, result.size());
        assertEquals("Content", result.get(0).getContent());
    }

    void showSpecificNoteFromUser(){

    }

    @Test
    void updateNote() {

    }

    @Test
    void deleteNote() {

    }
}
