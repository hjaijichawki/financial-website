package com.octest.beans;

public class Personnel {
    private String P_ID;
    private float Salaire;
    private String Appartenance;
    private int Year;

    public Personnel() {}

    public Personnel(String p_ID, float salaire, String appartenance, int year) {
        this.P_ID = p_ID;
        this.Salaire = salaire;
        this.Appartenance = appartenance;
        this.Year = year;
    }

    public String getP_ID() {
        return P_ID;
    }

    public void setP_ID(String p_ID) {
        this.P_ID = p_ID;
    }

    public float getSalaire() {
        return Salaire;
    }

    public void setSalaire(float salaire) {
        this.Salaire = salaire;
    }

    public String getAppartenance() {
        return Appartenance;
    }

    public void setAppartenance(String appartenance) {
        this.Appartenance = appartenance;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }
}