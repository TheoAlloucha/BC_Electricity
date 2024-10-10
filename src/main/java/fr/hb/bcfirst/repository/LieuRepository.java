package fr.hb.bcfirst.repository;

import fr.hb.bcfirst.model.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LieuRepository  extends JpaRepository<Lieu, Long> {
}
