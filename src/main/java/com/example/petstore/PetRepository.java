package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import com.example.petstore.*;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PetRepository implements PanacheRepository<Pet> {
	public Pet findByPetId(int id) {
		return find("petId", id).firstResult();
	}

	public List<Pet> findByPetName(String name) {
		return find("petName", name).list();
	}

	public List<Pet> findByPetAge(String age) {
		return find("petName", age).list();
	}

}