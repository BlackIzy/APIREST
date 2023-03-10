package api.controllers;

import api.dtos.ConcertDto;
import api.services.impl.ConcertServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/concerts")
public class ConcertController {
    private final ConcertServiceImpl concertService;

    public ConcertController(ConcertServiceImpl concertService) {
        this.concertService = concertService;
    }

    /**
     * <p>Get all concerts in the system</p>
     * @return List<ConcertDto>
     */
    @GetMapping
    public List<ConcertDto> getConcerts() {
        return concertService.getAllConcerts();
    }

    /**
     * Method to get the concert based on the ID
     */
    @GetMapping("/{id}")
    public ConcertDto getConcert(@PathVariable Long id){
        return concertService.getConcertById(id);
    }

    /**
     * Create a new Concert in the system
     */
    @PostMapping
    public ConcertDto saveConcert(final @RequestBody ConcertDto concertDto){
        return concertService.saveConcert(concertDto);
    }

    /**
     * Create a new Concert in the system
     */
    @PutMapping
    public ConcertDto updateConcert(final @RequestBody ConcertDto concertDto){
        return concertService.updateConcert(concertDto);
    }

    /**
     * Delete a concert by it's id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteConcert(@PathVariable Long id){
        return concertService.deleteConcert(id);
    }
}
