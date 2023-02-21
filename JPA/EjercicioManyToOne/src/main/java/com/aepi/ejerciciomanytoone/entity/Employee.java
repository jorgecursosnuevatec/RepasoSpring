package com.aepi.ejerciciomanytoone.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(name = "email")
	private String email;

	@Column(name = "nombre")
	private String name;

	@Column(name = "password")
	private String password;

	@OneToOne(mappedBy = "employee", optional = false, cascade = CascadeType.ALL)
	private Address address;

	@ManyToOne(fetch = FetchType.EAGER)
	private Department department;

	public Employee() {
	}

	public Employee(String email, String name, String password, Address address, Department department) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
		this.department = department;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", address="
				+ address + ", department=" + department + "]";
	}
}
