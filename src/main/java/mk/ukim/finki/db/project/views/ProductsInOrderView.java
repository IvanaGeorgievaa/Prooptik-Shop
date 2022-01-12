package mk.ukim.finki.db.project.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Immutable
@Table(name = "produkti_vo_naracka", schema = "internet_prodavnica")
public class ProductsInOrderView {
    @Id
    private Integer idNaracka;

    private String slikaProdukt;

    private String imeProdukt;

    private Integer kolicina;

    private Double cenaProdukt;

    private Double vkupno;
}
