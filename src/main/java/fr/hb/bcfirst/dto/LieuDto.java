package fr.hb.bcfirst.dto;

import fr.hb.bcfirst.model.Borne;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link fr.hb.bcfirst.model.Lieu}
 */
@Value
public class LieuDto implements Serializable {
    Long id;
    @NotBlank(message = "Merci de préciser un numéro")
    String numero;
    @NotBlank(message = "Merci de préciser une rue")
    String rue;
    @NotBlank(message = "Merci de préciser une ville")
    String ville;
    @NotBlank(message = "Merci de préciser un code postal")
    String codePostal;
    float latitude;
    float longitude;
    List<Borne> bornes;
}