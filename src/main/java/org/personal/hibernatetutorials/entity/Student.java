package org.personal.hibernatetutorials.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name = "studenttbl")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentid")
	private int id;

	@Column(name = "firstname", length = 20)
	private String fname;

	@Column(name = "lastname", length = 20)
	private String lname;
	
	@ElementCollection
	@JoinTable(name = "address", joinColumns = @JoinColumn(name = "studentid"))	
	List<Address> listofAddress = new ArrayList<>();
	
	public Student() {
		super();
	}
	
	

	public Student(String fname, String lname, List<Address> listofAddress) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.listofAddress = listofAddress;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public List<Address> getListofAddress() {
		return listofAddress;
	}

	public void setListofAddress(List<Address> listofAddress) {
		this.listofAddress = listofAddress;
	}
	
	
}
