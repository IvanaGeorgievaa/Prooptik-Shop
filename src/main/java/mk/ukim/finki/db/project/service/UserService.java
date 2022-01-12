package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Client;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Client register(String username, String password, String repeatPassword, String name, String surname, String address, String email);
}
