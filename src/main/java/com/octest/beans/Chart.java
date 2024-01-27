package com.octest.beans;

public class Chart {
    private int year;
    private float dAdministration;
    private float dRestaurant;
    private float dFoyer;
    private float dSiSy;
    private float dSysCo;
    private float dEGeS;

    public Chart(int year, float dAdministration, float dRestaurant, float dFoyer, float dSiSy, float dSysCo, float dEGeS) {
        this.year = year;
        this.dAdministration = dAdministration;
        this.dRestaurant = dRestaurant;
        this.dFoyer = dFoyer;
        this.dSiSy = dSiSy;
        this.dSysCo = dSysCo;
        this.dEGeS = dEGeS;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getDAdministration() {
        return dAdministration;
    }

    public void setDAdministration(float dAdministration) {
        this.dAdministration = dAdministration;
    }

    public float getDRestaurant() {
        return dRestaurant;
    }

    public void setDRestaurant(float dRestaurant) {
        this.dRestaurant = dRestaurant;
    }

    public float getDFoyer() {
        return dFoyer;
    }

    public void setDFoyer(float dFoyer) {
        this.dFoyer = dFoyer;
    }

    public float getDSiSy() {
        return dSiSy;
    }

    public void setDSiSy(float dSiSy) {
        this.dSiSy = dSiSy;
    }

    public float getDSysCo() {
        return dSysCo;
    }

    public void setDSysCo(float dSysCo) {
        this.dSysCo = dSysCo;
    }

    public float getDEGeS() {
        return dEGeS;
    }

    public void setDEGeS(float dEGeS) {
        this.dEGeS = dEGeS;
    }
}
