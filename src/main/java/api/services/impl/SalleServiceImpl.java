package api.services.impl;

import api.dtos.ConcertDto;
import api.dtos.SalleDto;
import api.dtos.SoireeDto;
import api.entities.Concert;
import api.entities.Salle;
import api.entities.Soiree;
import api.repositories.ConcertRepository;
import api.repositories.SalleRepository;
import api.repositories.SoireeRepository;
import api.services.SalleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service("salleService")
public class SalleServiceImpl implements SalleService {

    private SalleRepository salleRepository;
    private SoireeRepository soireeRepository;


    SalleServiceImpl(SalleRepository salleRepository, SoireeRepository soireeRepository, ConcertRepository concertRepository){
        this.soireeRepository = soireeRepository;
        this.salleRepository = salleRepository;
    }

    /**
     * @param salleDto
     * @return
     */
    @Override
    public SalleDto saveSalle(SalleDto salleDto) {
        Salle salle = this.salleDtoToEntity(salleDto);
        salle = this.salleRepository.save(salle);
        return this.salleEntityToDto(salle);
    }

    /**
     * @param salleDto
     * @return
     */
    @Override
    public SalleDto updateSalle(SalleDto salleDto) {
        SalleDto salleDto1 = this.getSalleById(salleDto.getCode_salle());
        Salle salle = null;
        if(salleDto1 != null){
            salleDto1.setNom(salleDto.getNom());
            salleDto1.setCapacite(salleDto.getCapacite());
            if(salleDto.getSoirees() != null){
                salleDto1.setSoirees(salleDto.getSoirees());
            }
            salle = this.salleRepository.save(this.salleDtoToEntity(salleDto1));
        }


        return this.salleEntityToDto(salle);
    }

    /**
     * @param salleId
     * @return
     */
    @Override
    public SalleDto getSalleById(Long salleId) {
        Salle salle = this.salleRepository.findById(salleId).orElseThrow(() -> new EntityNotFoundException("Soiree not found"));
        return this.salleEntityToDto(salle);
    }

    /**
     * @param salleId
     * @return
     */
    @Override
    public boolean deleteSalle(Long salleId) {
        this.salleRepository.deleteById(salleId);
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<SalleDto> getAllSalles() {
        List<Salle> salles = this.salleRepository.findAll();
        List<SalleDto> salleDtoList = new ArrayList<>();
        if(salles != null){
            salles.forEach(salle -> {
                salleDtoList.add(this.salleEntityToDto(salle));
            });
        }
        return salleDtoList;
    }

    /**
     * Map salle dto to salle entity
     */
    public SalleDto salleEntityToDto(Salle salle){
        SalleDto salleDto = new SalleDto();
        salleDto.setCode_salle(salle.getCode_salle());
        salleDto.setNom(salle.getNom());
        salleDto.setCapacite(salle.getCapacite());
        return salleDto;
    }

    /**
     * Map salle entity to salle dto
     */
    public Salle salleDtoToEntity(SalleDto salleDto){
        Salle salle = new Salle();
        salle.setCode_salle(salleDto.getCode_salle());
        salle.setNom(salleDto.getNom());
        salle.setCapacite(salleDto.getCapacite());
        return salle;
    }
}
