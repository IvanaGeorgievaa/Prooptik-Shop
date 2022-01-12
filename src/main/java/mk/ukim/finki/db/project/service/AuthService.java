package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Client;

public interface AuthService {

    Client login(String username, String password);
}
