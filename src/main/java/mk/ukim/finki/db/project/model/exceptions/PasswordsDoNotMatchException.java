package mk.ukim.finki.db.project.model.exceptions;


public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException(){
        super("Passwords do not match. Try again!");
    }
}
