package com.octest.beans;

public class Departement {
	  private int D_id;
	    private String D_name;
	    private int D_priority;
	    private String materials;
	    private int bankAccount_RIB;
	    
	    public Departement(int D_id, String D_name, int D_priority, String materials, int bankAccount_RIB) {
	        this.D_id = D_id;
	        this.D_name = D_name;
	        this.D_priority = D_priority;
	        this.materials = materials;
	        this.bankAccount_RIB = bankAccount_RIB;
	    }

		public int getD_id() {
			return D_id;
		}

		public void setD_id(int d_id) {
			D_id = d_id;
		}

		public String getD_name() {
			return D_name;
		}

		public void setD_name(String d_name) {
			D_name = d_name;
		}

		public int getD_priority() {
			return D_priority;
		}

		public void setD_priority(int d_priority) {
			D_priority = d_priority;
		}

		public String getMaterials() {
			return materials;
		}

		public void setMaterials(String materials) {
			this.materials = materials;
		}

		public int getBankAccount_RIB() {
			return bankAccount_RIB;
		}

		public void setBankAccount_RIB(int bankAccount_RIB) {
			this.bankAccount_RIB = bankAccount_RIB;
		}
}
