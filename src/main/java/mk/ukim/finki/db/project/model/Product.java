package mk.ukim.finki.db.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produkt", schema = "internet_prodavnica")
public class Product {
    @Id
    @Column(name = "id_produkt")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ime_produkt")
    private String name;

    @Column(name = "kolicina")
    private Integer quantity;

    @Column(name = "opis_produkt")
    private String description;

    @Column(name = "slika_produkt")
    private String picture;

    @Column(name = "cena_produkt")
    private Double price;

    @JoinColumn(name = "id_kategorija")
    @ManyToOne
    private Category category;

}
