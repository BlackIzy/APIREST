package api.services;

import api.dtos.SoireeDto;

import java.util.List;

public interface SoireeService {
    /**
     * Sauve a soiree
     */
    SoireeDto saveSoiree(SoireeDto soireeDto);

    /**
     * Update  soiree
     */
    SoireeDto updateSoiree(SoireeDto soireeDto);

    /**
     * Get a soiree by it's id
     */
    SoireeDto getSoireeById(Long soireeId);

    /**
     * Delete a soiree by it's id
     */
    boolean deleteSoiree(Long soireeId);

    /**
     * Get all the soirees
     */
    List<SoireeDto> getAllSoirees();
    
}
