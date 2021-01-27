package org.acme.services;

import org.acme.config.MQConnectionFactoryData;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;

@ApplicationScoped
public class MessageSenderService {
    @ConfigProperty(name = "com.bmw.ibm.mq.queue-name")
    String QUEUE_NAME;

    @Inject
    @MQConnectionFactoryData
    ConnectionFactory mqConnectionFactory;

    public void sendMessage(String message) {
        try(final JMSContext context = mqConnectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            final JMSProducer producer = context.createProducer();
            final Destination destination = context.createQueue(QUEUE_NAME);
            final TextMessage textMessage = context.createTextMessage(message);
            producer.send(destination, textMessage);
        }
    }
}
