package es.notes.notes.controller;

import es.notes.notes.model.AppUser;
import es.notes.notes.model.Note;
import es.notes.notes.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
public class TestNoteController {

    //    Inyecta el simulador http para enviar peticiones
    @Autowired
    private MockMvc mockMvc;


    public void mustShowAllNoteFromUser()throws Exception{

    }

    @Test
    public void saveNoteForTest() throws Exception {
        /*String newNoteJson = "{\"title\": \"Note1\", \"content\": \"This is the first note\", \"completed\": false, \"owner\":{\"id\": \"1\", \"username\": \"testUser\"}}";
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Note1");
        note.setContent("This is the first note");

        AppUser user = new AppUser();
        user.setUsername("testUser");
        user.setId(1L);

        mockMvc.perform(post("/notes/create")
                        .with(user("testUser").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Note1")
                        .param("content", "This is the first note")
                        .param("completed", "false"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notes"));
*/
    }
}