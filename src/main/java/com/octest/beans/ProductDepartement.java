package com.octest.beans;

public class ProductDepartement {
	  private int productId;
	    private int departementId;
	    
	    public ProductDepartement(int productId, int departementId) {
	        this.productId = productId;
	        this.departementId = departementId;
	    }

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public int getDepartementId() {
			return departementId;
		}

		public void setDepartementId(int departementId) {
			this.departementId = departementId;
		}
}
