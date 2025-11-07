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
        user.setRole("USER");

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
        when(noteRepository.findAll()).thenReturn(List<Note> notes);
    }

    void showSpecificNoteFromUser(){
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        Optional<Note> found = noteService.findByIdFor(1L,"user1");
        assertTrue(found.isPresent());
        assertEquals("Title", found.get().getTitle());
    }

    @Test
    void updateNote() {
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note updated = noteService.updateByIdFor(1L, new Note("New", "Updated"), "user1");

        assertEquals("New", updated.getTitle());
        verify(noteRepository).save(any(Note.class));
    }

    @Test
    void deleteNote() {
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        noteService.deleteByIdFor(1L, "user1");

        verify(noteRepository).deleteById(1L);
    }
}
