package fr.hb.bcfirst.controller;


import fr.hb.bcfirst.dto.LieuDto;
import fr.hb.bcfirst.mapper.LieuMapper;
import fr.hb.bcfirst.model.Lieu;
import fr.hb.bcfirst.service.LieuService;
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
@RequestMapping("api/lieux")
@CrossOrigin(origins = "http://localhost:3000")

public class LieuController {
    private LieuService lieuService;
    private LieuMapper lieuMapper;

    @GetMapping("/")
    @Operation(description = "Méthode qui récupère les lieux.")
    public ResponseEntity<Iterable<Lieu>> getLieux() {
        Iterable<Lieu> roleList = lieuService.recupererLieux();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Méthode qui récupère un lieu par ID")
    public ResponseEntity<Lieu> getLieuxById(@PathVariable long id) {
        return lieuService.recupererLieu(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(description = "Méthode qui ajoute un lieu")
    @ResponseStatus(HttpStatus.CREATED)
    public Lieu postLieu(@Valid @RequestBody LieuDto lieuDto) {
        Lieu lieu = lieuMapper.toEntity(lieuDto);
        return lieuService.enregistrerLieu(lieu);
    }

    @PutMapping()
    @Operation(description = "Méthode qui modifie un lieu")
    public ResponseEntity<Lieu> putLieu(@RequestBody LieuDto lieuDto) {

        Lieu lieu = lieuService.modifierLieu(lieuMapper.toEntity(lieuDto));
        if (lieu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(lieu);
        }

    }

    @DeleteMapping("/{id}")
    @Operation(description = "Méthode qui supprime un lieu par ID")
    public ResponseEntity<Void> supprimerLieu(@PathVariable long id) {
        return lieuService.supprimerLieu(id);
    }
}
