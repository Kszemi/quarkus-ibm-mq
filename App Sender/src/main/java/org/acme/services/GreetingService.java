package org.acme.services;

import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@Getter
@ApplicationScoped
public class GreetingService extends GreetingAbstract{

    @ConfigProperty(name = "greeting.message")
    String message;


    @Override
    public String greet() {
        return message;
    }
}
