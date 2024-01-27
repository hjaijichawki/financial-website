package com.octest.beans;

public class Product {
	private int p_id;
    private String p_name;
    private int d_id;
    private int number;
    private int p_priority;
    private double price;

    public Product(int p_id, String p_name, int d_id, int number, int p_priority, double price) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.d_id = d_id;
        this.number = number;
        this.p_priority = p_priority;
        this.price = price;
    }

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getP_priority() {
		return p_priority;
	}

	public void setP_priority(int p_priority) {
		this.p_priority = p_priority;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
