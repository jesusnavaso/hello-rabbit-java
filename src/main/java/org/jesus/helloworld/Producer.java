package org.jesus.helloworld;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Producer {

  static final String QUEUE_NAME = "patata";
  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      String message = "Hello Rabbit!";
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      System.out.printf(" [x] Sent '%s'", message);
    }
  }
}