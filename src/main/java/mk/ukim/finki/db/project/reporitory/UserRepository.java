package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Client, String> {
    Optional<Client> findByUsernameAndPassword(String username, String password);
    Optional<Client> findByUsername(String username);
}
