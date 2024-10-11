package fr.hb.bcfirst.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "Bornes")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Borne {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Merci de préciser un nom")
    @Column(length=100, nullable = false)
    private String nom;

    @NotBlank(message="Merci de préciser des instructions")
    @Column(length=100, nullable = false)
    private String instruction;

    @Column(length=100, nullable = false)
    private boolean isOnPied;

    @Column(length=100, nullable = false)
    private float tauxHoraire;

    @NotBlank(message="Merci de préciser un lien vers la photo")
    @Column(length=100, nullable = false)
    private String photo;

    @NotBlank(message="Merci de préciser un lien vers la video")
    @Column(length=100, nullable = false)
    private String video;

    @Column(length=100, nullable = false)
    private float puissance;

    @ManyToOne(cascade = { CascadeType.MERGE})
    @NotNull(message= "Merci de préciser le lieu")
    private Lieu lieu;

    public Borne(String nom, String instruction, boolean isOnPied, float tauxHoraire, String photo, String video, float puissance, Lieu lieu) {
        this.nom = nom;
        this.instruction = instruction;
        this.isOnPied = isOnPied;
        this.tauxHoraire = tauxHoraire;
        this.photo = photo;
        this.video = video;
        this.puissance = puissance;
        this.lieu = lieu;
    }

    public Borne() {

    }
}
