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

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	@Inject
	PetRepository repo;

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		List<Pet> pets = new ArrayList<Pet>();
		Pet pet1 = new Pet();

		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();

		return Response.ok(pet).build();

	}

	@POST
	@Transactional
	public Response insertPet(@RequestBody Pet pet) {
		repo.persist(pet);
		return Response.ok(pet).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	public Response loadAll() {

		List<Pet> pet = repo.listAll();
		return Response.ok(pet).build();
	}

	@GET
	@Path("/{petId}")
	public Response findByPetId(@PathParam("petId") int id) {
		Pet type = repo.findByPetId(id);
		return Response.ok(type).build();
	}

	@GET
	@Path("/name/{petName}")
	public Response findByPetName(@PathParam("petName") String name) {
		List<Pet> type = repo.findByPetName(name);
		return Response.ok(type).build();
	}

	@GET
	@Path("/age/{age}")
	public Response findByPetAge(@PathParam("petName") String age) {
		List<Pet> type = repo.findByPetAge(age);
		return Response.ok(type).build();
	}

	@PUT
	@Transactional
	public Response update(@RequestBody Pet type) {
		// System.out.println(type.getPetId());
		Pet currentType = repo.findByPetId(type.getPetId());
		currentType.setPetName(type.getPetName());
		currentType.setPetAge(type.getPetAge());

		repo.persist(currentType);

		return Response.ok(currentType).build();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Response delete(@PathParam("id") int id) {
		Pet type = repo.findByPetId(id);
		repo.delete(type);

		return Response.ok(type).build();
	}

}
