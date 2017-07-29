package org.personal.hibernatetutorials.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;

	@Column(name = "peraddress", length = 20)
	private String permanentAddress;

	@Column(name = "tempaddress", length = 20)
	private String temporaryAddress;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private List<Student> student = new ArrayList<>();

	public Address() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}
	
	

}
