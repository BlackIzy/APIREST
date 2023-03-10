package api.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link api.entities.Salle} entity
 */
@Data
public class SalleDto implements Serializable {
    private  Long code_salle;
    private  String nom;
    private  Integer capacite;

    public Long getCode_salle() {
        return code_salle;
    }

    public void setCode_salle(Long code_salle) {
        this.code_salle = code_salle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public List<SoireeDto> getSoirees() {
        return soirees;
    }

    public void setSoirees(List<SoireeDto> soirees) {
        this.soirees = soirees;
    }

    private  List<SoireeDto> soirees;
}