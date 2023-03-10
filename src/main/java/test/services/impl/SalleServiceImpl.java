package test.services.impl;

import test.dtos.SalleDto;
import test.entities.Salle;
import test.repositories.SalleRepository;
import test.services.SalleService;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalleServiceImpl implements SalleService {

    private final SalleRepository salleRepository;

    public SalleServiceImpl(SalleRepository salleRepository){
        this.salleRepository = salleRepository;
    }
    @Override
    public SalleDto saveSalle(SalleDto salleDto) {
        // Converts the dto to the dog entity
        Salle salle = salleDtoToEntity(salleDto);
        // Save the dog entity
        salle = salleRepository.save(salle);
        // Return the new dto
        return salleEntityToDto(salle);
    }

    @Override
    public SalleDto getSalleById(Long salleId) {
        Salle salle = salleRepository.findById(salleId).orElseThrow(() -> new EntityNotFoundException("Salle not found"));
        return salleEntityToDto(salle);
    }

    @Override
    public boolean deleteSalle(Long salleId) {
        salleRepository.deleteById(salleId);
        return true;
    }

    @Override
    public List<SalleDto> getAllSalles() {
        List<SalleDto> salleDtos = new ArrayList<>();
        List<Salle> salles = salleRepository.findAll();
        salles.forEach(salle -> {
            salleDtos.add(salleEntityToDto(salle));
        });
        return salleDtos;
    }

    private SalleDto salleEntityToDto(Salle salle){
        SalleDto salleDto = new SalleDto();
        salleDto.setId(salle.getId());
        salleDto.setNom(salle.getNom());
        salleDto.setCapacite(salle.getCapacite());
        salleDto.setConcert(salle.getConcert());
        return salleDto;
    }

    private Salle salleDtoToEntity(SalleDto salleDto){
        Salle salle = new Salle();
        salle.setId(salleDto.getId());
        salle.setNom(salleDto.getNom());
        salle.setCapacite(salleDto.getCapacite());
        salle.setConcert(salleDto.getConcert());
        return salle;
    }
}
