package es.notes.notes.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // Añado propietario nota. Relación muchos a uno, cada nota pertenece a un AppUser.
    // Lazy: Para carga perezosa...Al leer una nota, si a priori no necesitas el dueño, la bbdd solo te trae la nota.
    // Ventajas: rendimiento (menos datos si no necesitas el dueño).
    // Y por último mapeo la columna FK owner_id en la tala note. Si quisieras obligar a que haya dueño podemos: nullable=false
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

    public AppUser() {}

    public AppUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
