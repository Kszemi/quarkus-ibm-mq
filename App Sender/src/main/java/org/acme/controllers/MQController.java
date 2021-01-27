package org.acme.controllers;

import org.acme.services.MessageSenderService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/mq")
public class MQController {

    @Inject
    MessageSenderService senderService;

    @GET()
    @Path("send")
    public void send(){
        senderService.sendMessage("meeessssaaagggeee, mmmeeeesssssaaagggeee");
    }
}
