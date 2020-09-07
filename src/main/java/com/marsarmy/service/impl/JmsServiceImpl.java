package com.marsarmy.service.impl;

import com.marsarmy.service.interf.JmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * Service responsible for JMS
 */
@Service
public class JmsServiceImpl implements JmsService {

    private final JmsTemplate jmsTemplate;
    private final static String TOPIC_NAME = "UpdateTopic";

    @Autowired
    public JmsServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Puts a message in the topic to update statistics in the advertising stands
     */
    @Override
    public void sendUpdate() {
        MessageCreator messageCreator = session -> session.createTextMessage("update");
        jmsTemplate.send(TOPIC_NAME, messageCreator);
    }
}
