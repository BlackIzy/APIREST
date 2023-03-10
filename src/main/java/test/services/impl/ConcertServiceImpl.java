package test.services.impl;

import test.dtos.ConcertDto;
import test.entities.Concert;
import test.repositories.ConcertRepository;
import test.services.ConcertService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;

    public ConcertServiceImpl(ConcertRepository concertRepository){
        this.concertRepository = concertRepository;
    }
    @Override
    public ConcertDto saveConcert(ConcertDto concertDto) {
        // Converts the dto to the dog entity
        Concert concert = concertDtoToEntity(concertDto);
        // Save the dog entity
        concert = concertRepository.save(concert);
        // Return the new dto
        return concertEntityToDto(concert);
    }

    @Override
    public ConcertDto getConcertById(Long concertId) {
        Concert concert = concertRepository.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        return concertEntityToDto(concert);
    }

    @Override
    public boolean deleteConcert(Long concertId) {
        concertRepository.deleteById(concertId);
        return true;
    }

    @Override
    public List<ConcertDto> getAllConcerts() {
        List<ConcertDto> concertDtos = new ArrayList<>();
        List<Concert> concerts = concertRepository.findAll();
        concerts.forEach(concert -> {
            concertDtos.add(concertEntityToDto(concert));
        });
        return concertDtos;
    }

    private ConcertDto concertEntityToDto(Concert concert){
        ConcertDto concertDto = new ConcertDto();
        concertDto.setId(concert.getId());
        concertDto.setDate_debut(concert.getDate_debut());
        concertDto.setDate_fin(concertDto.getDate_fin());
        concertDto.setSalle(concertDto.getSalle());
        return concertDto;
    }

    private Concert concertDtoToEntity(ConcertDto concertDto){
        Concert concert = new Concert();
        concert.setId(concertDto.getId());
        concert.setDate_debut(concertDto.getDate_debut());
        concert.setDate_fin(concertDto.getDate_fin());
        concert.setSalle(concertDto.getSalle());
        return concert;
    }
}
