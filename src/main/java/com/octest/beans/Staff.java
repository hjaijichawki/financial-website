package com.octest.beans;

public class Staff {
	private int d_id;
    private int s_id;
    private String s_full_name;
    private int s_priority;
    private double salary;

    public Staff(int d_id, int s_id, String s_full_name, int s_priority, double salary) {
        this.d_id = d_id;
        this.s_id = s_id;
        this.s_full_name = s_full_name;
        this.s_priority = s_priority;
        this.salary = salary;
    }

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getS_full_name() {
		return s_full_name;
	}

	public void setS_full_name(String s_full_name) {
		this.s_full_name = s_full_name;
	}

	public int getS_priority() {
		return s_priority;
	}

	public void setS_priority(int s_priority) {
		this.s_priority = s_priority;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
