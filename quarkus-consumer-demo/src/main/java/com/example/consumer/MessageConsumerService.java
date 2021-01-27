package com.example.consumer;

import com.example.store.MessageService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class MessageConsumerService implements Runnable{

    @ConfigProperty(name = "com.bmw.ibm.mq.queue-name")
    String QUEuE_NAME;

    @Inject
    @MQConnectionFactoryData
    ConnectionFactory connectionFactory;

    @Inject
    MessageService messageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerService.class);
    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();

    void onStart(@Observes StartupEvent event) {
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent event) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try(JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            Queue queue = context.createQueue(QUEuE_NAME);
            JMSConsumer consumer = context.createConsumer(queue);
            while (true) {
                Message message = consumer.receive();
                if (Objects.isNull(message)) return;
                String messageBody = message.getBody(String.class);
                String savedMessage = messageService.saveMessage(messageBody);
                LOGGER.info("Message saved: {}", savedMessage);
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }
}
