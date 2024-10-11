package fr.hb.bcfirst.service;

import fr.hb.bcfirst.model.Borne;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BorneService {

    Borne enregistrerBorne(Borne borne);

    Borne modifierBorne(Borne borne);

    Optional<Borne> recupererBorne(Long idBorne);

    List<Borne> recupererBornes();

    ResponseEntity<Void> supprimerBorne(Long idBorne);

}
