package test.dtos;

import test.entities.Concert;
import lombok.Data;


@Data
public class SalleDto {

    private Long Id;

    private String nom;

    private int capacite;

    private Concert concert;
}
