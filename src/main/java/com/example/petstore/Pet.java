package com.example.petstore;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;

// defining entity and table
@Entity
@Table
public class Pet {

	@Id // primary key
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int petId;

	@Column(unique = true) // unique column
	private String petName;

	@Column
	private int petAge;

	@JoinColumn(name = "petTypeId", referencedColumnName = "petTypeId")
	@ManyToOne(optional = false)
	private PetType petTypeId;

	public int getPetId() {
		return petId;
	}

	public void setPetId(int id) {
		this.petId = id;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public PetType getPetTypeId() {
		return petTypeId;
	}

	public void setPetTypeId(PetType petTypeId) {
		this.petTypeId = petTypeId;
	}

}