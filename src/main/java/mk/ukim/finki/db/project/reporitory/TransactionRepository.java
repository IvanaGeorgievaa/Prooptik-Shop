package mk.ukim.finki.db.project.reporitory;

import mk.ukim.finki.db.project.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
