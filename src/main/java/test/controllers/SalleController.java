package test.controllers;

import test.dtos.SalleDto;
import org.springframework.web.bind.annotation.*;

import test.services.impl.SalleServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/salle")

public class SalleController {

    private final SalleServiceImpl salleService;

    public SalleController(SalleServiceImpl salleService) {
        this.salleService = salleService;
    }

    /**
     * <p>Get all dogs in the system</p>
     * @return List<DogDto>
     */
    @GetMapping
    public List<SalleDto> getSalles() {
        return salleService.getAllSalles();
    }

    @GetMapping("/{id}")
    public SalleDto getSalle(@PathVariable Long id){
        return salleService.getSalleById(id);
    }

    @PostMapping
    public SalleDto saveSalle(final @RequestBody SalleDto SalleDto){
        return salleService.saveSalle(SalleDto);
    }

    /**
     * Delete a dog by it's id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteSalle(@PathVariable Long id){
        return salleService.deleteSalle(id);
    }

}
