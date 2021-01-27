package com.example.consumer;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.JMSException;
import javax.ws.rs.Produces;

import static com.ibm.msg.client.jms.JmsConstants.*;
import static com.ibm.msg.client.wmq.common.CommonConstants.WMQ_CM_CLIENT;

@ApplicationScoped
public class QueueConfiguration {

    @ConfigProperty(name = "com.bmw.ibm.mq.host")
    String host;

    @ConfigProperty(name = "com.bmw.ibm.mq.port")
    int port;

    @ConfigProperty(name = "com.bmw.ibm.mq.channel")
    String channel;

    @ConfigProperty(name = "com.bmw.ibm.mq.username")
    String username;

    @ConfigProperty(name = "com.bmw.ibm.mq.password")
    String password;

    @ConfigProperty(name = "com.bmw.ibm.mq.queue-manager")
    String queueManager;

    @Produces
    @MQConnectionFactoryData
    public MQConnectionFactory getConnectionFactory() throws JMSException {
        MQConnectionFactory connectionFactory = new MQConnectionFactory();
        connectionFactory.setTransportType(WMQ_CM_CLIENT);
        connectionFactory.setChannel(channel);
        connectionFactory.setQueueManager(queueManager);
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setStringProperty(USERID, username);
        connectionFactory.setStringProperty(PASSWORD, password);
        connectionFactory.setBooleanProperty(USER_AUTHENTICATION_MQCSP, true);
        connectionFactory.setIntProperty(CommonConstants.WMQ_CLIENT_RECONNECT_OPTIONS,
                CommonConstants.WMQ_CLIENT_RECONNECT);

        return connectionFactory;
    }
}
