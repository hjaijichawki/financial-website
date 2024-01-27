package com.octest.beans;


public class Years {
    public float getDepartement_t() {
		return departement_t;
	}

	public void setDepartement_t(float departement_t) {
		this.departement_t = departement_t;
	}

	public float getDepartement_d() {
		return departement_d;
	}

	public void setDepartement_d(float departement_d) {
		this.departement_d = departement_d;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getdAdministration() {
		return dAdministration;
	}

	public void setdAdministration(float dAdministration) {
		this.dAdministration = dAdministration;
	}

	public float getAdT() {
		return adT;
	}

	public void setAdT(float adT) {
		this.adT = adT;
	}

	public float getdRestaurant() {
		return dRestaurant;
	}

	public void setdRestaurant(float dRestaurant) {
		this.dRestaurant = dRestaurant;
	}

	public float getResT() {
		return resT;
	}

	public void setResT(float resT) {
		this.resT = resT;
	}

	public float getdFoyer() {
		return dFoyer;
	}

	public void setdFoyer(float dFoyer) {
		this.dFoyer = dFoyer;
	}


	public float getFoT() {
		return foT;
	}

	public void setFoT(float foT) {
		this.foT = foT;
	}



	public float getTotalDepense() {
		return totalDepense;
	}

	public void setTotalDepense(float totalDepense) {
		this.totalDepense = totalDepense;
	}

	public float getTotalTolere() {
		return totalTolere;
	}

	public void setTotalTolere(float totalTolere) {
		this.totalTolere = totalTolere;
	}

	private int year;
    
    private float dAdministration;
    
    private float adT;
    
    private float dRestaurant;
    
    private float resT;
    
    private float dFoyer;
    
    private float foT;
    
    
    private float departement_t;
    private float departement_d;


   
    
    private float totalDepense;
    
    private float totalTolere;
    private String etat;

    public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Years() {
        // Empty constructor
    }
    
    // Getters and setters
    // ...
}
