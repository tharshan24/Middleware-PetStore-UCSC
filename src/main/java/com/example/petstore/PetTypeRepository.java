package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import com.example.petstore.*;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PetTypeRepository implements PanacheRepository<PetType> {

	public PetType findByPetTypeId(int id) {
		return find("idPetType", id).firstResult();
	}
}