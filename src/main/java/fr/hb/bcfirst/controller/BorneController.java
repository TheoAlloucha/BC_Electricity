package fr.hb.bcfirst.controller;

import fr.hb.bcfirst.dto.BorneDto;
import fr.hb.bcfirst.mapper.BorneMapper;
import fr.hb.bcfirst.model.Borne;
import fr.hb.bcfirst.service.BorneService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("api/bornes")
@CrossOrigin(origins = "http://localhost:3000")

public class BorneController {
    private BorneService borneService;
    private BorneMapper borneMapper;

    @GetMapping("/")
    @Operation(description = "Méthode qui récupère les bornes.")
    public ResponseEntity<Iterable<Borne>> getBornes() {
        Iterable<Borne> roleList = borneService.recupererBornes();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Méthode qui récupère une borne par ID")
    public ResponseEntity<Borne> getBorneById(@PathVariable long id) {
        return borneService.recupererBorne(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    @Operation(description = "Méthode qui ajoute une borne")
    @ResponseStatus(HttpStatus.CREATED)
    public Borne postBorne(@Valid @RequestBody BorneDto borneDto) {
        Borne borne = borneMapper.toEntity(borneDto);
        return borneService.enregistrerBorne(borne);
    }

    @PutMapping()
    @Operation(description = "Méthode qui modifie une borne")
    public ResponseEntity<Borne> putBorne(@RequestBody BorneDto borneDto) {

        Borne borne = borneService.modifierBorne(borneMapper.toEntity(borneDto));
        if (borne == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(borne);
        }

    }

    @DeleteMapping("/{id}")
    @Operation(description = "Méthode qui supprime une borne par ID")
    public ResponseEntity<Void> supprimerBorne(@PathVariable long id) {
        return borneService.supprimerBorne(id);
    }
}
