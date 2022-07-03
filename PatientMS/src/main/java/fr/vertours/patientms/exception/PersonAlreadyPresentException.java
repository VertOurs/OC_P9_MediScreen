package fr.vertours.patientms.exception;



public class PersonAlreadyPresentException extends RuntimeException {

    private final String firstName;
    private final String lastName;

    public PersonAlreadyPresentException(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getMessage() {
        return "this user : " + firstName + " " + lastName  + ", was already present in database";
    }
}
