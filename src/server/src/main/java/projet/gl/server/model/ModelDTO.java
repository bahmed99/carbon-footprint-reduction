package projet.gl.server.model;

import java.time.LocalDate;

public class ModelDTO {

    private Long id;
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Long brandId;

    public ModelDTO() {
    }

    public ModelDTO(String name) {
        this.name = name;
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    public ModelDTO(Long id, String name, LocalDate createdAt, LocalDate updatedAt, Long brandId) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.brandId = brandId;
    }

    public ModelDTO(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
        this.brandId = model.getBrandId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

}