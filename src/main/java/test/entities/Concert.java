package test.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

import java.util.List;

@Entity
@Data
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concert")
    private Long Id;
    private Date date_debut;

    private Date date_fin;

    @OneToMany(mappedBy = "Id")
    private List<Salle> salle;





}
