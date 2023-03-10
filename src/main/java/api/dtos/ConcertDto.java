package api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A DTO for the {@link api.entities.Concert} entity
 */
@Data
public class ConcertDto implements Serializable {
    private  Long code_concert;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private  LocalDateTime date_debut;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private  LocalDateTime date_fin;
    private  Double prix;
    private  SoireeDto soiree;
}