package fr.hb.bcfirst.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "lieux")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @Column(length=100, nullable = false)
    private float latitude;

    @Column(length=100, nullable = false)
    private float longitude;

    @OneToMany(mappedBy = "lieu")
    private List<Borne> bornes;

    public Lieu(String numero, String rue, String ville, String codePostal, float latitude, float longitude) {
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Lieu() {

    }
}
