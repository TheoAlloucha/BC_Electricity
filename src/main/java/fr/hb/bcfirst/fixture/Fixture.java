package fr.hb.bcfirst.fixture;

import fr.hb.bcfirst.model.Lieu;
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

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ajout des fixtures");
        Instant debut = Instant.now();
        ajouterLieu();

        Instant fin = Instant.now();

        System.out.println("Ajouts effectués en " + (fin.toEpochMilli() - debut.toEpochMilli()) + " ms");

    }

    private void ajouterLieu(){
        lieuRepository.save(new Lieu("1", "rue de lavenue", "Perpignan", "66000", (float)19.8923, (float) 12.1420 ));
        lieuRepository.save(new Lieu("2", "rue de la rue", "Perpignan", "66000", (float)13.8923, (float) 12.3220 ));
        lieuRepository.save(new Lieu("3", "rue du boulevard", "Perpignan", "66000", (float)15.8923, (float) 14.9320 ));
        lieuRepository.save(new Lieu("4", "rue de l'impasse", "Perpignan", "66000", (float)16.8923, (float) 11.9320 ));
        lieuRepository.save(new Lieu("5", "rue de du pavilon", "Perpignan", "66000", (float)17.8923, (float) 12.9320 ));


    }
}
