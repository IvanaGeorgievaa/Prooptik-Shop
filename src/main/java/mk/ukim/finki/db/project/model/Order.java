package mk.ukim.finki.db.project.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "naracka", schema = "internet_prodavnica")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_naracka")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "datum")
    private Date date;

    @JoinColumn(name = "id_klient")
    @ManyToOne
    private Client client;

    @ManyToMany
    private List<Product> products;

    public Order(Client client) {
        this.client = client;
        this.status = OrderStatus.CREATED;
        this.date = Date.valueOf(LocalDate.now());
        this.products = new ArrayList<>();
    }
}

