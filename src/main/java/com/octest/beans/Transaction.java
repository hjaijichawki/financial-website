package com.octest.beans;

import java.sql.Date;

public class Transaction {
    private Date date;
    private String idTransaction;
    private String beneficiaire;
    private double montant;
    private String etat;
    private int year;

    public Transaction(Date date, String idTransaction, String beneficiaire, double montant, String etat, int year) {
        this.date = date;
        this.idTransaction = idTransaction;
        this.beneficiaire = beneficiaire;
        this.montant = montant;
        this.etat = etat;
        this.year = year;
    }

    // Getters and Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
