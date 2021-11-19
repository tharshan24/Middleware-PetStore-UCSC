package com.example.petstore;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;

// defining entity and table
@Entity
@Table
public class PetType {

	@Id // primary key
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int petTypeId;

	@Column(unique = true) // unique column
	private String petTypeName;

	public int getPetTypeId() {
		return petTypeId;
	}

	public void setPetTypeId(int id) {
		this.petTypeId = id;
	}

	public String getPetTypeName() {
		return petTypeName;
	}

	public void setPetTypeName(String petName) {
		this.petTypeName = petName;
	}

	public Collection<Pet> getPetCollection() {
		return petCollection;
	}

	public void setPetCollection(Collection<Pet> petCollection) {
		this.petCollection = petCollection;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "petTypeId")
	private transient Collection<Pet> petCollection;

}