package com.example.store;

import com.example.entity.Message;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@Transactional
class MessageServiceImplQuarkusTest {

    @Inject
    MessageServiceImpl messageService;

    @Test
    public void shouldSaveAndGetMessages() {
        String message = "message";
        String storedMessage = "QUARKUS:message";
        messageService.saveMessage(message);

        List<Message> storeMessages = messageService.getMessages();

        assertThat(storeMessages, hasSize(1));
        assertThat(storeMessages.get(0).getText(), equalTo(storedMessage));
    }
}