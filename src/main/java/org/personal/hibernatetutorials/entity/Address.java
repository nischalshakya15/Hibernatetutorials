package org.personal.hibernatetutorials.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

	

@Embeddable
public class Address {

	@Column(name = "peraddress", length = 20)
	private String permanentAddress;
	
	@Column(name = "tempaddress", length = 20)
	private String temporaryAddress;
	
	public Address(){
		super();
	}

	public Address(String permanentAddress, String temporaryAddress) {
		super();
		this.permanentAddress = permanentAddress;
		this.temporaryAddress = temporaryAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}
	

}
