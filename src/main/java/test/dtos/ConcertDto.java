package test.dtos;

import test.entities.Salle;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ConcertDto {

    private Long Id;

    private Date date_debut;

    private Date date_fin;

    private List<Salle> salle;
}
