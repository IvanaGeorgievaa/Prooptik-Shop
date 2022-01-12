package mk.ukim.finki.db.project.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.db.project.model.OrderStatus;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Setter
@Entity
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "finished_orders", schema = "internet_prodavnica")
public class FinishedOrdersView {
    @Id
    private Integer id_naracka;

    private String username;

    private Integer id_klient;

    private String ime_klient;

    private Date datum;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
