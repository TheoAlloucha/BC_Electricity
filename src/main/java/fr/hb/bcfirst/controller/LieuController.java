package fr.hb.bcfirst.controller;


import fr.hb.bcfirst.model.Lieu;
import fr.hb.bcfirst.service.LieuService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("api/lieux")
@CrossOrigin(origins = "http://localhost:3000")

public class LieuController {
    @Autowired
    private LieuService lieuService;

    @GetMapping("/")
    @Operation(description = "Méthode qui récupère les lieux.")
    public ResponseEntity<Iterable<Lieu>> getLieux() {
        Iterable<Lieu> roleList = lieuService.recupererLieux();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }
}
