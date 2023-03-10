package api.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code_concert;

    private LocalDateTime date_debut;

    private LocalDateTime date_fin;

    private Double prix;

    @ManyToOne
    private Soiree soiree;

}