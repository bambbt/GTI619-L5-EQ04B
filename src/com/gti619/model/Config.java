package com.gti619.model;
// default package
// Generated Nov 26, 2016 12:48:25 PM by Hibernate Tools 5.1.0.Beta1

/**
 * Config generated by hbm2java
 */


public class Config implements java.io.Serializable {

	private Integer idconfig;

	private String salt;

	public Config() {
	}

	public Config(String salt) {
		this.salt = salt;
	}

	public Integer getIdconfig() {
		return this.idconfig;
	}

	public void setIdconfig(Integer idconfig) {
		this.idconfig = idconfig;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
