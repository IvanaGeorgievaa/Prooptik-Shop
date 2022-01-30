package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.Role;
import mk.ukim.finki.db.project.model.exceptions.*;
import mk.ukim.finki.db.project.reporitory.UserRepository;
import mk.ukim.finki.db.project.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(String username, String password, String repeatPassword, String name, String surname, String address, String email, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UserAlreadyExistsException(username);

        Client user = new Client(username, passwordEncoder.encode(password), name, surname, address, email, role);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client=this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPassword(),
                Stream.of(new SimpleGrantedAuthority(client.getRole().toString())).collect(Collectors.toList())
        );
    }
}

