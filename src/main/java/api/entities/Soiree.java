package api.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Soiree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code_soiree;

    private String nom;

    private LocalDate date;



    @OneToMany(mappedBy = "soiree")
    private List<Concert> concerts = new ArrayList<>();

    @ManyToOne
    private Salle salle;

}