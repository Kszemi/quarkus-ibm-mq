package org.acme.controllers;

import org.acme.entitites.Person;
import org.acme.services.PersonService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/person")
public class PersonController {

    @Inject
    PersonService personService;

    @GET
    @Path("people")
    public List<Person> getPeople() {
        return personService.findAll();
    }

    @POST
    @Path("/add/{name}")
    public Person addPerson(@PathParam("name")String name) {
        return personService.createPerson(name);
    }
}
