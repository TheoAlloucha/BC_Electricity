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
        Lieu lieu1 = new Lieu("1", "rue de lavenue", "Perpignan", "66000", 19.8923F, 12.1420F);
        Lieu lieu2 = new Lieu("2", "rue de la rue", "Perpignan", "66000", 13.8923F, 12.3220F);
        Lieu lieu3 = new Lieu("3", "rue du boulevard", "Perpignan", "66000", 15.8923F, 14.9320F);
        Lieu lieu4 = new Lieu("4", "rue de l'impasse", "Perpignan", "66000", 16.8923F, 11.9320F);
        Lieu lieu5 = new Lieu("5", "rue de du pavilon", "Perpignan", "66000", 17.8923F, 12.9320F);

        // Sauvegarde des lieux dans la base de données
        lieuRepository.saveAndFlush(lieu1);
        lieuRepository.saveAndFlush(lieu2);
        lieuRepository.saveAndFlush(lieu3);
        lieuRepository.saveAndFlush(lieu4);
        lieuRepository.saveAndFlush(lieu5);

        // Récupération des lieux à partir de la base pour s'assurer qu'ils sont attachés
        Lieu lieuFind1 = lieuRepository.findById(lieu1.getId()).orElseThrow();
        Lieu lieuFind2 = lieuRepository.findById(lieu2.getId()).orElseThrow();

        // Sauvegarde des bornes avec les lieux récupérés
        borneRepository.save(new Borne("NEW BORNE", "instruction", true, 1.2F, "photo", "video", 1.2F, lieuFind1));
        borneRepository.save(new Borne("NEW BORNE2", "instruction", true, 3F, "photo", "video", 42F, lieuFind1));
        borneRepository.save(new Borne("NEW BORNE3", "instruction", true, 2.0F, "photo", "video", 2.3F, lieuFind2));
    }


}
