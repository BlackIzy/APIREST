package test.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String nom;

    private int capacite;

    @JoinColumn(name = "id_concert", referencedColumnName = "id_concert")
    @ManyToOne
    private Concert concert;


}
