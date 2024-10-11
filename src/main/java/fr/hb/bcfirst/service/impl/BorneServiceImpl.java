package fr.hb.bcfirst.service.impl;

import fr.hb.bcfirst.model.Borne;
import fr.hb.bcfirst.repository.BorneRepository;
import fr.hb.bcfirst.service.BorneService;
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
public class BorneServiceImpl implements BorneService {
    
    @Autowired
    private BorneRepository borneRepository;

    @Override
    public Borne enregistrerBorne(Borne borne) {
        return borneRepository.save(borne);
    }

    @Override
    public Borne modifierBorne(Borne borne) {
        Borne itemFind = borneRepository.getById(borne.getId());
        itemFind.setLieu(borne.getLieu());
        itemFind.setNom(borne.getNom());
        itemFind.setInstruction(borne.getInstruction());
        itemFind.setPhoto(borne.getPhoto());
        itemFind.setVideo(borne.getVideo());
        itemFind.setTauxHoraire(borne.getTauxHoraire());
        itemFind.setOnPied(borne.isOnPied());

        return borneRepository.save(itemFind);
    }


    @Override
    public Optional<Borne> recupererBorne(Long idBorne) {
        return borneRepository.findById(idBorne);
    }


    @Override
    public List<Borne> recupererBornes() {
        return borneRepository.findAll();
    }

    @Override
    public ResponseEntity<Void> supprimerBorne(Long idBorne) {
        borneRepository.deleteById(idBorne);
        return null;
    }

}
