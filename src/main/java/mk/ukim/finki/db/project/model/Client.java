package mk.ukim.finki.db.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.db.project.model.enumerations.Role;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "klient", schema = "internet_prodavnica")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klient")
    private Integer id;

    private String username;

    private String password;

    @Column(name = "ime_klient")
    private String name;

    @Column(name = "prezime_klient")
    private String surname;

    private String email;

    @Column(name = "adresa")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Order>orders;


    public Client(String username, String password, String name, String surname, String address, String email, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email=email;
        this.role=role;
    }

}
