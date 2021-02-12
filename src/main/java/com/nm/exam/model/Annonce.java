package com.nm.exam.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
    @NotNull(message = "Le champ titre ne doit pas etre nul")
    @NotBlank(message = "Le champ ne peut pas etre blanc !")
    private String titre;

    @Column(name = "contenu")
    @NotNull(message = "Le champ contenu doit faire au moins 20 caracteres")
    @NotBlank(message = "Le champ ne peut pas etre blanc !")
    private String contenu;

    @Column(name = "date_publication")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Le champ date ne peut pas etre nul")
    @Past(message = "La date ne dois pas etre dans le future")
    private Date datePublication;

    @Column(name = "image")
    @NotNull(message = "Veuillez chosiir une image")
    @NotBlank(message = "Le image ne peut pas etre blanc !")
    private String image;

    public Annonce() {
    }

    public Annonce(Long id, String titre, String contenu, Date datePublication, String image) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.image = image;
    }

    public Annonce(String titre, String contenu, Date datePublication, String image) {
        this.titre = titre;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.image = image;
    }


    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    
    public Date getDatePublication() {
        return datePublication;
    }
    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Annonce annonce = (Annonce) o;

        if (id != null ? !id.equals(annonce.id) : annonce.id != null) return false;
        if (titre != null ? !titre.equals(annonce.titre) : annonce.titre != null) return false;
        if (contenu != null ? !contenu.equals(annonce.contenu) : annonce.contenu != null) return false;
        if (datePublication != null ? !datePublication.equals(annonce.datePublication) : annonce.datePublication != null)
            return false;
        return image != null ? !image.equals(annonce.image) : annonce.image == null;
        
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        result = 31 * result + (contenu != null ? contenu.hashCode() : 0);
        result = 31 * result + (datePublication != null ? datePublication.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "annonce{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", datePublication=" + datePublication +
                ", image='" + image + '\'' +
                '}';
    }
}
