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
import api.services.SoireeService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service("soireeService")
public class SoireeServiceImpl implements SoireeService {
    private final SoireeRepository soireeRepository;
    private final SalleRepository salleRepository;

    private final ConcertRepository concertRepository;

    /**
     * @param
     * @return
     */
    public SoireeServiceImpl(SoireeRepository soireeRepository,
                             SalleRepository salleRepository, ConcertRepository concertRepository){
        this.soireeRepository = soireeRepository;
        this.salleRepository = salleRepository;
        this.concertRepository = concertRepository;
    }

    /**
     * @param soireeDto
     * @return
     */
    @Override
    public SoireeDto saveSoiree(SoireeDto soireeDto) {
        Soiree soirre = this.soireeDtoToEntity(soireeDto);
        soirre = soireeRepository.save(soirre);
        return this.soireeEntityToDto(soirre);
    }

    /**
     * @param soireeDto
     * @return
     */
    @Override
    public SoireeDto updateSoiree(SoireeDto soireeDto) {
        SoireeDto soireeDto1 = this.getSoireeById(soireeDto.getCode_soiree());
        Soiree soiree = null;
        if(soireeDto1 != null){
            soireeDto1.setNom(soireeDto.getNom());
            soireeDto1.setDate(soireeDto.getDate());
            if(soireeDto.getSalle() != null){
                soireeDto1.setSalle(soireeDto.getSalle());
            }
            if(soireeDto1.getConcerts() != null){
                soireeDto1.setConcerts(soireeDto.getConcerts());
            }
            soiree = this.soireeRepository.save(this.soireeDtoToEntity(soireeDto1));
        }


        return this.soireeEntityToDto(soiree);
    }

    /**
     * @param soireeId
     * @return
     */
    @Override
    public SoireeDto getSoireeById(Long soireeId) {
        Soiree soiree = this.soireeRepository.findById(soireeId).orElseThrow(() -> new EntityNotFoundException("Soiree not found"));
        return soireeEntityToDto(soiree);
    }

    /**
     * @param soireeId
     * @return
     */
    @Override
    public boolean deleteSoiree(Long soireeId) {
        if(this.getSoireeById(soireeId) != null){
            soireeRepository.deleteById(soireeId);
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return
     */
    @Override
    public List<SoireeDto> getAllSoirees() {
        List<Soiree> soirees = soireeRepository.findAll();
        List<SoireeDto> soireeDtos = new ArrayList<>();
        soirees.forEach(dog -> {
            soireeDtos.add(this.soireeEntityToDto(dog));
        });
        return soireeDtos;
    }

    /**
     * Map salle dto to salle entity
     */
    public SoireeDto soireeEntityToDto(Soiree soiree){
        SoireeDto soireeDto = new SoireeDto();
        soireeDto.setCode_soiree(soiree.getCode_soiree());
        soireeDto.setDate(soiree.getDate());
        soireeDto.setNom(soiree.getNom());
        if(soiree.getConcerts() != null){
            ConcertServiceImpl concertImpl = new ConcertServiceImpl(this.concertRepository,
                    this.soireeRepository, this.salleRepository);
            List<ConcertDto> concertDtoList= new ArrayList<>();
            for(Concert c : soiree.getConcerts()){
                concertDtoList.add(concertImpl.concertEntityToDto(c));
            }
            if(soiree.getSalle() != null){
                SalleServiceImpl salleImpl = new SalleServiceImpl(this.salleRepository, this.soireeRepository, this.concertRepository);
                SalleDto salleDto = salleImpl.salleEntityToDto(soiree.getSalle());

                soireeDto.setSalle(salleDto);
            }
            soireeDto.setConcerts(concertDtoList);
        }


        return soireeDto;
    }

    /**
     * Map salle entity to salle dto
     */
    public Soiree soireeDtoToEntity(SoireeDto soireeDto){
        Soiree soiree = new Soiree();
        soiree.setCode_soiree(soireeDto.getCode_soiree());
        soiree.setDate(soireeDto.getDate());
        soiree.setNom(soireeDto.getNom());


        if(soireeDto.getConcerts() != null){
            List<Concert> concerts = new ArrayList<>();
            ConcertServiceImpl concertImpl = new ConcertServiceImpl(this.concertRepository,
                    this.soireeRepository, this.salleRepository);
            for(ConcertDto c : soireeDto.getConcerts()){
                concerts.add(concertImpl.concertDtoToEntity(c));
            }
            if(soireeDto.getSalle() != null){
                SalleServiceImpl salleImpl = new SalleServiceImpl(this.salleRepository, this.soireeRepository, this.concertRepository);
                Salle salle = salleImpl.salleDtoToEntity(soireeDto.getSalle());
                soiree.setSalle(salle);
            }
            soiree.setConcerts(concerts);
        }


        return soiree;
    }
}
