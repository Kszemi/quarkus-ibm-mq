package com.example.store;

import com.example.entity.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplUnitTest {

    @InjectMocks
    MessageServiceImpl messageService;

    @Mock
    EntityManager entityManager;

    @Mock
    TypedQuery<Message> messageTypedQuery;

    @Test
    public void shouldSaveMessage() {
        String message = "test";

        messageService.saveMessage(message);

        verify(entityManager).persist(any(Message.class));
    }

    @Test
    public void shouldGetMessages() {
        Message message = new Message();
        String testText = "test";
        message.setText(testText);
        List<Message> messages = Collections.singletonList(message);
        when(entityManager.createNamedQuery(Message.GET_ALL_MESSAGES, Message.class)).thenReturn(messageTypedQuery);
        when(messageTypedQuery.getResultList()).thenReturn(messages);

        List<Message> messageResultList = messageService.getMessages();

        verify(entityManager).createNamedQuery(Message.GET_ALL_MESSAGES, Message.class);
        verify(messageTypedQuery).getResultList();
        assertThat(messageResultList, hasSize(1));
        assertThat(messageResultList.get(0).getText(), equalTo(testText));
    }
}