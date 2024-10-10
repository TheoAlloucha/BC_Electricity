package fr.hb.bcfirst.service;

import fr.hb.bcfirst.model.Lieu;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface LieuService {

    Lieu enregistrerLieu(Lieu lieu);

    Lieu modifierLieu(Lieu lieu);

    Optional<Lieu> recupererLieu(Long idLieu);

    List<Lieu> recupererLieux();

    ResponseEntity<Void> supprimerLieu(Long idLieu);

}
