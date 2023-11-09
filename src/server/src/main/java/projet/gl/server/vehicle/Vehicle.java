package projet.gl.server.vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import projet.gl.server.color.Color;
import projet.gl.server.configuration.Configuration;
import projet.gl.server.model.Model;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year_of_creation")
    private int yearOfCreation;

    @Column(name = "price_without_configuration")
    private double priceWithoutConfiguration;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToMany
    @JoinTable(name = "vehicle_configuration", joinColumns = @JoinColumn(name = "vehicle_id"), inverseJoinColumns = @JoinColumn(name = "configuration_id"))
    private Set<Configuration> configurations = new HashSet<>();

    public Vehicle() {
        // This is a default constructor that is required by JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public double getPriceWithoutConfiguration() {
        return priceWithoutConfiguration;
    }

    public void setPriceWithoutConfiguration(double priceWithoutConfiguration) {
        this.priceWithoutConfiguration = priceWithoutConfiguration;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Set<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Set<Configuration> configurations) {
        this.configurations = configurations;
    }

    public void copy(Vehicle v) {
        this.yearOfCreation = v.getYearOfCreation();
        this.priceWithoutConfiguration = v.getPriceWithoutConfiguration();
        this.model = v.getModel();
        this.color = v.getColor();
        this.configurations = v.getConfigurations();
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDate.now();
    }

}
