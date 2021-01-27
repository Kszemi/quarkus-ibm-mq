package com.example.store;

import com.example.entity.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class MessageServiceImpl implements MessageService {

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public String saveMessage(String message) {
        Message messageToStore = new Message();
        messageToStore.setText("QUARKUS:" + message);
        entityManager.persist(messageToStore);

        return messageToStore.getText();
    }

    @Override
    public List<Message> getMessages() {
        TypedQuery<Message> query = entityManager.createNamedQuery(Message.GET_ALL_MESSAGES, Message.class);
        return query.getResultList();
    }
}
