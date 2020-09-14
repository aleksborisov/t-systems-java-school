package com.marsarmy.service.impl;

import com.marsarmy.service.interf.JmsService;
import org.apache.log4j.Logger;
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
    private static final Logger LOGGER = Logger.getLogger(JmsServiceImpl.class);

    @Autowired
    public JmsServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Puts a message in the topic to update statistics in advertising stands
     */
    @Override
    public void sendUpdate() {
        MessageCreator messageCreator = session -> session.createTextMessage("update");
        jmsTemplate.send(TOPIC_NAME, messageCreator);
        LOGGER.info("Statistics update message was sent to the stands");
    }
}
