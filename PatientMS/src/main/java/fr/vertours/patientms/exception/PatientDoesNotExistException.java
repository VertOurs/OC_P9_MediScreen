package fr.vertours.patientms.exception;

public class PatientDoesNotExistException extends RuntimeException {

    private final long id;

    public PatientDoesNotExistException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "this id : " + id + ", was not found in database";
    }
}
