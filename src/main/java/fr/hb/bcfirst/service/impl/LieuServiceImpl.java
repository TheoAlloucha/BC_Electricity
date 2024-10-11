package fr.hb.bcfirst.service.impl;


import fr.hb.bcfirst.model.Lieu;
import fr.hb.bcfirst.repository.LieuRepository;
import fr.hb.bcfirst.service.LieuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class LieuServiceImpl implements LieuService {

    @Autowired
    private LieuRepository lieuRepository;

    @Override
    public Lieu enregistrerLieu(Lieu lieu) {
        return lieuRepository.save(lieu);
    }

    @Override
    public Lieu modifierLieu(Lieu lieu) {
        Lieu itemFind = lieuRepository.getById(lieu.getId());
        itemFind.setRue(lieu.getRue());
        itemFind.setNumero(lieu.getNumero());
        itemFind.setVille(lieu.getVille());
        itemFind.setCodePostal(lieu.getCodePostal());
        itemFind.setLatitude(lieu.getLatitude());
        itemFind.setLongitude(lieu.getLongitude());
        return lieuRepository.save(itemFind);
    }

    @Override
    public Optional<Lieu> recupererLieu(Long idLieu) {
        return lieuRepository.findById(idLieu);
    }

    @Override
    public List<Lieu> recupererLieux() {
        return lieuRepository.findAll();
    }

    @Override
    public ResponseEntity<Void> supprimerLieu(Long idLieu) {
        lieuRepository.deleteById(idLieu);
        return null;
    }

}
