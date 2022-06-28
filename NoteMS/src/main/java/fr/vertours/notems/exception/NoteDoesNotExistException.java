package fr.vertours.notems.exception;

public class NoteDoesNotExistException extends RuntimeException {

    private final String id;

    public NoteDoesNotExistException(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "this id : " + id + ", was not found in database";
    }
}
