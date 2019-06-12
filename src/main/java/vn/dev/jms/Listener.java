package vn.dev.jms;

import com.google.gson.Gson;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Listener {

  @JmsListener(destination = "inbound.topic")
  @SendTo("outbound.queue")
  public String receiveMessage(final Message message) throws JMSException {
    String messageData = null;
    System.out.println("Received message " + message);
    String response = null;
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      messageData = textMessage.getText();
      Map map = new Gson().fromJson(messageData, Map.class);
      response = "Hello " + map.get("name");
    }
    return message.toString();
  }

  @JmsListener(destination = "inbound.topic")
  @SendTo("outbound.queue")
  public String receiveMessage1(final Message message) throws JMSException {
    String messageData = null;
    System.out.println("Received message " + message);
    String response = null;
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      messageData = textMessage.getText();
      Map map = new Gson().fromJson(messageData, Map.class);
      response = "Hello adfdf" + map.get("name");
    }
    return response;
  }

  @JmsListener(destination = "outbound.queue")
  public void receiverOut(final Message message) {
    System.out.println("Nhan tu outbuond" + message);
  }



}
