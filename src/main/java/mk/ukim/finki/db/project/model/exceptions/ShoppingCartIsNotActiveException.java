package mk.ukim.finki.db.project.model.exceptions;

import mk.ukim.finki.db.project.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ShoppingCartIsNotActiveException extends RuntimeException {
    public ShoppingCartIsNotActiveException(String userId) {
        super(String.format("There is no active shopping cart for user %s!", userId));
    }
}

