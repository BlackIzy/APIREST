package test.services;

import test.dtos.SalleDto;

import java.util.List;

public interface SalleService {
    /**
     * Sauve a dog
     */
    SalleDto saveSalle(SalleDto salleDto);

    /**
     * Get a dog by it's id
     */
    SalleDto getSalleById(Long salleId);

    /**
     * Delete a dog by it's id
     */
    boolean deleteSalle(Long salleId);

    /**
     * Get all the dogs
     */
    List<SalleDto> getAllSalles();
}
