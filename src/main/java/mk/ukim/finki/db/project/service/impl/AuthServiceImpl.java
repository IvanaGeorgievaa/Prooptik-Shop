package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.db.project.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.db.project.reporitory.UserRepository;
import mk.ukim.finki.db.project.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Client login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
