package org.acme.controllers;

import org.acme.entitites.Apple;
import org.acme.entitites.Person;
import org.acme.services.GreetingService;
import org.acme.services.PersonService;
import org.acme.utils.Status;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/quarkus")
public class GreetingController {

    @Inject
    GreetingService greetingService;

    @Inject
    PersonService personService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("hello")
    public String hello() {

        return greetingService.getMessage();
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("hello/{user}")
    public String helloUser(@PathParam("user") String user) {
        return greetingService.getMessage() + " dear " + user;
    }








}
