package org.chtijbug.drools.jms;

import org.chtijbug.drools.ReverseProxyUpdate;
import org.springframework.jms.core.MessageCreator;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class ReverseProxyMessageCreator implements MessageCreator {


    private ReverseProxyUpdate reverseProxyUpdate;

    public ReverseProxyMessageCreator(ReverseProxyUpdate reverseProxyUpdate) {
        this.reverseProxyUpdate = reverseProxyUpdate;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        ObjectMessage objectMessage = session.createObjectMessage();

        objectMessage.setObject(reverseProxyUpdate);
        return objectMessage;
    }
}
