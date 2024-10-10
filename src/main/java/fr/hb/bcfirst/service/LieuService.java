package fr.hb.bcfirst.service;

import fr.hb.bcfirst.model.Lieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LieuService {

    Lieu enregistrerLieu(Lieu lieu);

    Lieu modifierLieu(Lieu lieu);

    Optional<Lieu> recupererLieu(Long idLieu);

    List<Lieu> recupererLieux();

    void supprimerLieu(Long idLieu);

}
