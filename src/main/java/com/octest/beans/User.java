package com.octest.beans;

public class User {
    public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String uId;
    private String password;
    private String fullName;
    private String secretCode;
    private String email;
    private String name ;

    // Constructor
    public User() {};
    public User(String uId, String password, String fullName, String secretCode, String email, String name) {
        this.uId = uId;
        this.password = password;
        this.fullName = fullName;
        this.secretCode = secretCode;
        this.email = email;
        this.name = name ;
    }

}
