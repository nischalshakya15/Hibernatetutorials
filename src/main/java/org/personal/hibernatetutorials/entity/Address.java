package org.personal.hibernatetutorials.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

	

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign",parameters=@Parameter(name="property", value="student"))
	@Column(name = "studentid")
	private int id;

	@Column(name = "peraddress", length = 20)
	private String permanentAddress;

	@Column(name = "tempaddress", length = 20)
	private String temporaryAddress;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Student student;

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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
