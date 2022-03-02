package mk.ukim.finki.db.project.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInWishListException extends RuntimeException{

    public ProductAlreadyInWishListException(Integer id, String username) {
        super(String.format("Product with id: %d already exists in wish list for user with username %s", id, username));
    }
}