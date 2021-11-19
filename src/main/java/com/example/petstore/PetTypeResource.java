package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.*;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/types")
@Produces("application/json")
public class PetTypeResource {

	@Inject
	PetTypeRepository repo;

	@GET
	public Response loadAll() {

		List<PetType> petTypes = repo.listAll();
		return Response.ok(petTypes).build();
	}

	@POST
	@Transactional
	public Response insertPetType(@RequestBody PetType type) {
		repo.persist(type);
		return Response.ok(type).build();
	}

	@GET
	@Path("{id}")
	public Response searchById(@PathParam("id") int id) {
		PetType type = repo.findByPetTypeId(id);
		return Response.ok(type).build();
	}

	@PUT
	@Transactional
	public Response update(@RequestBody PetType type) {
		PetType currentType = repo.findByPetTypeId(type.getIdPetType());
		currentType.setName(type.getName());
		currentType.setPetCollection(type.getPetCollection());
		repo.persist(currentType);
		return Response.ok(currentType).build();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Response delete(@PathParam("id") int id) {
		PetType type = repo.findByPetTypeId(id);
		type.setStatus(0);

		return Response.ok(type).build();
	}
}