package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.Order;
import mk.ukim.finki.db.project.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByClientAndStatus(Client client, OrderStatus status);
}
