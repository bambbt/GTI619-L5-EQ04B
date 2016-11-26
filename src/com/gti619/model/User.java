package com.gti619.model;
// default package
// Generated Nov 26, 2016 12:48:25 PM by Hibernate Tools 5.1.0.Beta1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer iduser;
	private Role role;
	private String login;
	private String mdp;

	public User() {
	}

	public User(Role role) {
		this.role = role;
	}

	public User(Role role, String login, String mdp) {
		this.role = role;
		this.login = login;
		this.mdp = mdp;
	}

	public Integer getIduser() {
		return this.iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
