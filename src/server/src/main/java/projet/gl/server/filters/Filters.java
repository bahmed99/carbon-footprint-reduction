package projet.gl.server.filters;

import java.util.List;

public class Filters {



    private List<Integer> Brands = null ;
    private List<Integer> Models = null;
    private List<Integer> Colors = null;
    private List<Integer> Configurations = null;

    public Filters() {
    }

    public List<Integer> getBrands() {
        return Brands;
    }

    public void setBrands(List<Integer> brands) {
        Brands = brands;
    }

    public List<Integer> getModels() {
        return Models;
    }

    public void setModels(List<Integer> models) {
        Models = models;
    }

    public List<Integer> getColors() {
        return Colors;
    }

    public void setColors(List<Integer> colors) {
        Colors = colors;
    }

    public List<Integer> getConfigurations() {
        return Configurations;
    }

    public void setConfigurations(List<Integer> configurations) {
        Configurations = configurations;
    }

    
}
