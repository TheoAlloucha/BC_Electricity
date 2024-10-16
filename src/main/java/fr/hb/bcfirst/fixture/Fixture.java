package fr.hb.bcfirst.fixture;

import fr.hb.bcfirst.model.Borne;
import fr.hb.bcfirst.model.Lieu;
import fr.hb.bcfirst.repository.BorneRepository;
import fr.hb.bcfirst.repository.LieuRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component("ajoutDonneesInitiales")
@Profile("dev")
@AllArgsConstructor
public class Fixture implements CommandLineRunner {

    private LieuRepository lieuRepository;

    private BorneRepository borneRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ajout des fixtures");
        Instant debut = Instant.now();
        ajouterLieu();

        Instant fin = Instant.now();

        System.out.println("Ajouts effectués en " + (fin.toEpochMilli() - debut.toEpochMilli()) + " ms");
    }

    private void ajouterLieu() {
        Lieu lieu1 = new Lieu("1", "rue de lavenue", "Perpignan", "66000", 43.96920F, 2.28480F);
        Lieu lieu2 = new Lieu("2", "rue de la rue", "Perpignan", "66000", 45.73620F, 1.87280F);
        Lieu lieu3 = new Lieu("3", "rue du boulevard", "Perpignan", "66000", 42.92830F, 2.29080F);
        Lieu lieu4 = new Lieu("4", "rue de l'impasse", "Perpignan", "66000", 48.34120F, 2.31280F);
        Lieu lieu5 = new Lieu("5", "rue de du pavilon", "Perpignan", "66000", 48.34560F, 1.49280F);

        // Sauvegarde des lieux dans la base de données
        lieuRepository.saveAndFlush(lieu1);
        lieuRepository.saveAndFlush(lieu2);
        lieuRepository.saveAndFlush(lieu3);
        lieuRepository.saveAndFlush(lieu4);
        lieuRepository.saveAndFlush(lieu5);

        // Récupération des lieux à partir de la base pour s'assurer qu'ils sont attachés
        Lieu lieuFind1 = lieuRepository.findById(lieu1.getId()).orElseThrow();
        Lieu lieuFind2 = lieuRepository.findById(lieu2.getId()).orElseThrow();
        Lieu lieuFind3 = lieuRepository.findById(lieu3.getId()).orElseThrow();
        Lieu lieuFind4 = lieuRepository.findById(lieu4.getId()).orElseThrow();
        Lieu lieuFind5 = lieuRepository.findById(lieu5.getId()).orElseThrow();

        // Sauvegarde des bornes avec les lieux récupérés
        borneRepository.save(new Borne("NEW BORNE1", "instruction", true, 1.2F, "https://images.pexels.com/photos/28884704/pexels-photo-28884704/free-photo-of-une-petite-voiture-electrique-branchee-sur-une-borne-de-recharge.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "video", 1.2F, lieuFind1));
        borneRepository.save(new Borne("NEW BORNE2", "instruction", false, 3F, "https://images.pexels.com/photos/28884704/pexels-photo-28884704/free-photo-of-une-petite-voiture-electrique-branchee-sur-une-borne-de-recharge.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "video", 42F, lieuFind1));
        borneRepository.save(new Borne("NEW BORNE3", "instruction", true, 2.0F, "https://images.pexels.com/photos/28884704/pexels-photo-28884704/free-photo-of-une-petite-voiture-electrique-branchee-sur-une-borne-de-recharge.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "video", 2.3F, lieuFind2));
        borneRepository.save(new Borne("NEW BORNE4", "instruction", false, 12.0F, "https://images.pexels.com/photos/28884704/pexels-photo-28884704/free-photo-of-une-petite-voiture-electrique-branchee-sur-une-borne-de-recharge.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "video", 22.3F, lieuFind3));
        borneRepository.save(new Borne("NEW BORNE5", "instruction", true, 4.0F, "https://images.pexels.com/photos/28884704/pexels-photo-28884704/free-photo-of-une-petite-voiture-electrique-branchee-sur-une-borne-de-recharge.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "video", 7.1F, lieuFind4));
        borneRepository.save(new Borne("NEW BORNE6", "instruction", false, 3.8F, "https://images.pexels.com/photos/28884704/pexels-photo-28884704/free-photo-of-une-petite-voiture-electrique-branchee-sur-une-borne-de-recharge.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "video", 7.6F, lieuFind5));
    }


}
