package org.jugvale.peoplemanagement.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jugvale.peoplemanagement.model.Person;
import org.jugvale.peoplemanagement.service.PersonService;

@Path("person")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

	@Inject
	PersonService service;

	@GET
	@Path("rfid/{rfid}")
	public Person getPersonByRfid(@PathParam("rfid") String rfid) {
		return service.findByRFID(rfid);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Person p) {
		String rfid = p.getRfid();
		Person existing = service.findByRFID(rfid);
		if (existing != null) {
			return Response
					.status(Status.CONFLICT)
					.entity("Resource with RFID " + rfid
							+ " already registered").build();
		} else {
			service.save(p);
			return Response.status(Status.CREATED).entity(p).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") long id) {
		Person p = service.findById(id);
		if (p == null) {
			return Response.notModified()
					.entity("Resource with ID " + id + "not found.").build();
		} else {
			service.remove(p);
			return Response.ok().build();
		}
	}

	@GET
	public List<Person> getAll() {
		return service.listAll();
	}
}