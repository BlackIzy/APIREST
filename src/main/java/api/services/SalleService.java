package api.services;

import api.dtos.SalleDto;

import java.util.List;

public interface SalleService {
    /**
     * Save Salle
     */
    SalleDto saveSalle(SalleDto salleDto);

    /**
     * Update Salle
     */
    SalleDto updateSalle(SalleDto salleDto);


    /**
     * Get salle by it's id
     */
    SalleDto getSalleById(Long salleId);

    /**
     * Delete salle by it's id
     */
    boolean deleteSalle(Long salleId);

    /**
     * Get all salles
     */
    List<SalleDto> getAllSalles();
}
