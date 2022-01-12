package mk.ukim.finki.db.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "kategorija", schema = "internet_prodavnica")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kategorija")
    private Integer id;

    @Column(name = "ime_kategorija")
    private String name;
}

