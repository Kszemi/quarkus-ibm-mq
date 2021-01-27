package org.acme.controllers;

import org.acme.services.MessageSenderService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/mq")
public class MQController {

    @Inject
    MessageSenderService senderService;

    @POST
    @Path("send")
    @Consumes(MediaType.TEXT_PLAIN)
    public void send(String msg){
        senderService.sendMessage(msg);
    }
}
