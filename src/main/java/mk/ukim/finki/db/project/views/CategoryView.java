package mk.ukim.finki.db.project.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Immutable
@Table(name = "kategorii", schema = "internet_prodavnica")
public class CategoryView {
    @Id
    @Column(name = "id_kategorija")
    private Integer id;

    @Column(name = "ime_kategorija")
    private String name;
}
