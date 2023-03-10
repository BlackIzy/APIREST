package api.services.impl;

import api.dtos.ConcertDto;
import api.dtos.SoireeDto;
import api.entities.Concert;
import api.entities.Soiree;
import api.repositories.ConcertRepository;
import api.repositories.SalleRepository;
import api.repositories.SoireeRepository;
import api.services.ConcertService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service("concertService")
public class ConcertServiceImpl implements ConcertService {

    private ConcertRepository concertRepository;
    private SoireeRepository soireeRepository;

    private SalleRepository salleRepository;

    ConcertServiceImpl(ConcertRepository concertRepository, SoireeRepository soireeRepository, SalleRepository salleRepository){
        this.concertRepository = concertRepository;
        this.soireeRepository = soireeRepository;
        this.salleRepository = salleRepository;
    }
    /**
     * @param concertDto
     * @return
     */
    @Override
    public ConcertDto saveConcert(ConcertDto concertDto) {
        Concert concert = this.concertDtoToEntity(concertDto);
        concert = this.concertRepository.save(concert);
        return concertEntityToDto(concert);
    }

    /**
     * @param concertDto
     * @return
     */
    @Override
    public ConcertDto updateConcert(ConcertDto concertDto) {
        ConcertDto concertDto1 = this.getConcertById(concertDto.getCode_concert());
        Concert concert = null;
        if(concertDto1 != null){
            concertDto1.setDate_debut(concertDto.getDate_debut());
            concertDto1.setPrix(concertDto.getPrix());
            concertDto1.setDate_fin(concertDto.getDate_fin());
            concertDto1.setSoiree(concertDto.getSoiree());
            concert = this.concertRepository.save(this.concertDtoToEntity(concertDto1));
        }


        return this.concertEntityToDto(concert);
    }

    /**
     * @param concertId
     * @return
     */
    @Override
    public ConcertDto getConcertById(Long concertId) {
        Concert concert = this.concertRepository.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        return concertEntityToDto(concert);
    }

    /**
     * @param concertId
     * @return
     */
    @Override
    public boolean deleteConcert(Long concertId) {
        if(this.getConcertById(concertId) != null){
            this.concertRepository.deleteById(concertId);
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return
     */
    @Override
    public List<ConcertDto> getAllConcerts() {
        List<Concert> concerts = this.concertRepository.findAll();
        List<ConcertDto> concertDtoListe = new ArrayList<>();
        if(concerts == null){
            return null;
        }else{
            concerts.forEach(concert -> {
                concertDtoListe.add(this.concertEntityToDto(concert));
            });
        }
        return concertDtoListe;
    }

    /**
     * Map salle dto to salle entity
     */
    public ConcertDto concertEntityToDto(Concert concert){
        ConcertDto concertDto = new ConcertDto();
        concertDto.setCode_concert(concert.getCode_concert());
        concertDto.setPrix(concert.getPrix());
        concertDto.setDate_debut(concert.getDate_debut());
        concertDto.setDate_fin(concert.getDate_fin());
        if(concert.getSoiree() != null){
            SoireeDto soireeDto = new SoireeDto();
            SoireeServiceImpl soireeImp = new SoireeServiceImpl(this.soireeRepository, this.salleRepository, this.concertRepository);
            concertDto.setSoiree(soireeDto);
        }
        return concertDto;
    }

    /**
     * Map salle entity to salle dto
     */
    public Concert concertDtoToEntity(ConcertDto concertDto){
        Concert concert = new Concert();
        concert.setCode_concert(concertDto.getCode_concert());
        concert.setPrix(concertDto.getPrix());
        concert.setDate_debut(concertDto.getDate_debut());
        concert.setDate_fin(concertDto.getDate_fin());
        if(concertDto.getSoiree() != null){
            Soiree soiree = new Soiree();
            SoireeServiceImpl soireeImp = new SoireeServiceImpl(this.soireeRepository, this.salleRepository, this.concertRepository);
            soiree = soireeImp.soireeDtoToEntity(concertDto.getSoiree());
            concert.setSoiree(soiree);
        }

        return concert;
    }
}
