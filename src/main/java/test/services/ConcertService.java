package test.services;

import test.dtos.ConcertDto;
import java.util.List;

public interface ConcertService {
    /**
     * Sauve a dog
     */
    ConcertDto saveConcert(ConcertDto concertDto);

    /**
     * Get a dog by it's id
     */
    ConcertDto getConcertById(Long concertId);

    /**
     * Delete a dog by it's id
     */
    boolean deleteConcert(Long concertId);

    /**
     * Get all the dogs
     */
    List<ConcertDto> getAllConcerts();
}
