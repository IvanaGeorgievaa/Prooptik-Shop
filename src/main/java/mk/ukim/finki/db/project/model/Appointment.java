package mk.ukim.finki.db.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(schema = "internet_prodavnica")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_klient")
    @ManyToOne
    private Client client;

    private LocalDateTime date;

    public Appointment(Client client, LocalDateTime date) {
        this.client = client;
        this.date = date;
    }
}
