package org.example;

public class Vuelo {
    String aerolínea, origen, destino;
    int folio;
    float costo;

    public Vuelo(String aerolínea, String origen, String destino, int folio, float costo) {
        this.aerolínea = aerolínea;
        this.origen = origen;
        this.destino = destino;
        this.folio = folio;
        this.costo = costo;
    }

    public String getAerolínea() {
        return aerolínea;
    }

    public void setAerolínea(String aerolínea) {
        this.aerolínea = aerolínea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}
