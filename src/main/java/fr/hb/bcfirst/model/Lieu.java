package fr.hb.bcfirst.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Lieu {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Merci de préciser un numéro")
    @Column(length=100, nullable = false)
    private String numero;

    @NotBlank(message="Merci de préciser une rue")
    @Column(length=100, nullable = false)
    private String rue;

    @NotBlank(message="Merci de préciser une ville")
    @Column(length=100, nullable = false)
    private String ville;

    @NotBlank(message="Merci de préciser un code postal")
    @Column(length=100, nullable = false)
    private String codePostal;

    @NotBlank(message="Merci de préciser une latitude")
    @Column(length=100, nullable = false)
    private float latitude;

    @NotBlank(message="Merci de préciser une longitude")
    @Column(length=100, nullable = false)
    private float longitude;


}
