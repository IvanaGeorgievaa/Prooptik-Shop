package mk.ukim.finki.db.project.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WishListNotFoundException extends RuntimeException{

    public WishListNotFoundException(Integer id) {
        super(String.format("Wish list with id: %d was not found", id));
    }
}
