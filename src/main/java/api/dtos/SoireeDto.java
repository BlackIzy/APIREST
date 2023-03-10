package api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link api.entities.Soiree} entity
 */
@Data
public class SoireeDto implements Serializable {
    private Long code_soiree;
    private  String nom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private  LocalDate date;
    private  List<ConcertDto> concerts;
    private  SalleDto salle;
    public SoireeDto(){

    }
    SoireeDto(Long code_soiree, String nom, LocalDate date, List<ConcertDto> concerts, SalleDto salleDto){
        this.code_soiree = code_soiree;
        this.date = date;
        this.concerts = concerts;
        this.salle = salleDto;
    }
}