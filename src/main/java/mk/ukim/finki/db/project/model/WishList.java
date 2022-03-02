package mk.ukim.finki.db.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "internet_prodavnica")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_klient")
    @ManyToOne
    private Client client;

    @ManyToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private WishListStatus status;

    public WishList(Client client) {
        this.client = client;
        this.status=WishListStatus.CREATED;
        this.products=new ArrayList<>();
    }
}
