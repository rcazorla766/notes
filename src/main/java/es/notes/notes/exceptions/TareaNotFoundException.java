package es.notes.notes.exceptions;

public class TareaNotFoundException extends RuntimeException {
    public TareaNotFoundException(Long id) {
        super("No se pudo encontrar la tarea con ID "+ id);
    }
}
