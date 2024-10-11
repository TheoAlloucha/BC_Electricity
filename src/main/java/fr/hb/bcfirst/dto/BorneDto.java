package fr.hb.bcfirst.dto;

import fr.hb.bcfirst.model.Lieu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.awt.*;
import java.io.Serializable;

/**
 * DTO for {@link fr.hb.bcfirst.model.Borne}
 */
@Value
public class BorneDto implements Serializable {
    Long id;
    @NotBlank(message = "Merci de préciser un nom")
    String nom;
    String instruction;
    boolean isOnPied;
    float tauxHoraire;
    @NotBlank(message = "Merci de préciser un lien vers la photo")
    String photo;
    @NotBlank(message = "Merci de préciser un lien vers la video")
    String video;
    float puissance;
    @NotNull(message = "Merci de préciser le lieu")
    Lieu lieu;
}