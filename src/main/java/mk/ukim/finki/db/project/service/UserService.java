package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.enumerations.Role;

public interface UserService {
    void register(String username, String password, String repeatPassword, String name, String surname, String address, String email, Role role);
}
