package projet.gl.server.marque;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    public Marque() {
    }

    public Marque(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Marque(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nom='" + getNom() + "'" + "}";
    }
}
