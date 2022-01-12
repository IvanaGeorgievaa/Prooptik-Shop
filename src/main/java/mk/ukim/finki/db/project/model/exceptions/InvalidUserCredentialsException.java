package mk.ukim.finki.db.project.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid user credentials exception");
    }
}
