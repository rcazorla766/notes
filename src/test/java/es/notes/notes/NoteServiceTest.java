package es.notes.notes;

import es.notes.notes.model.Note;
import es.notes.notes.repository.NoteRepository;
import es.notes.notes.service.NoteService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void debeDevolverTodasLasTareas(){
        Note note1 =new Note();
        note1.setContent("Content1");
        Note note2 =new Note();
        note2.setContent("Content2");

        String username="user";

        List<Note> listaSimulada = Arrays.asList(note1,note2);

        when (noteRepository.findAll()).thenReturn(listaSimulada);

        List<Note> resultado = noteService.findAllFor(username);
    }
}
