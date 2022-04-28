package mk.ukim.finki.db.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "internet_prodavnica")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String transactionHash;

    @OneToOne
    private Order order;

    private String toAddress;

    private String fromAddress;

    private String amount;

    private LocalDateTime date;

    public Transaction(String transactionHash, Order order, String toAddress, String fromAddress, String amount) {
        this.transactionHash = transactionHash;
        this.order = order;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }
}
