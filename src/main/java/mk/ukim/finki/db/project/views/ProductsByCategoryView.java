package mk.ukim.finki.db.project.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Setter
@Entity
@Immutable
@NoArgsConstructor
@Table(name = "produkti_spored_kategorija", schema = "internet_prodavnica")
public class ProductsByCategoryView {

    @Id
    private Integer id_produkt;

    private Integer id_kategorija;

    private String slika_produkt;

    private Integer kolicina;

    private String ime_produkt;

    private Double cena_produkt;

    private String opis_produkt;

    private String ime_kategorija;

    public ProductsByCategoryView(Integer id_kategorija, String slika_produkt, Integer kolicina, String ime_produkt, Double cena_produkt, String opis_produkt) {
        this.id_kategorija = id_kategorija;
        this.slika_produkt = slika_produkt;
        this.kolicina = kolicina;
        this.ime_produkt = ime_produkt;
        this.cena_produkt = cena_produkt;
        this.opis_produkt = opis_produkt;
    }


}
