package com.octest.beans;


public class Outils {
    private String id;
    private float prix;
    private String appartenance;
    private int year;

    public Outils() {}

    public Outils(String id, float prix, String appartenance, int year) {
        this.id = id;
        this.prix = prix;
        this.appartenance = appartenance;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getAppartenance() {
        return appartenance;
    }

    public void setAppartenance(String appartenance) {
        this.appartenance = appartenance;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
