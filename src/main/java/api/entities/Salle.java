package api.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity Salle
 * auteur : Abdoulaye BERTE
 */
@Entity
@Getter
@Setter
@Data
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code_salle;

    private String nom;

    private Integer capacite;


    @OneToMany(mappedBy = "salle")
    private List<Soiree> soirees = new ArrayList<>();



}