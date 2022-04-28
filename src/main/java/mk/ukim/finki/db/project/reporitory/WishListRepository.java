package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.model.*;
import mk.ukim.finki.db.project.model.enumerations.WishListStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    Optional<WishList> findByClientAndStatus(Client client, WishListStatus status);
}
