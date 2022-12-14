package org.jesus.publishsubscribe;

import static org.jesus.publishsubscribe.EmitLog.EXCHANGE_NAME;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;

public class ReceiveLogs {

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
    // We create a non-durable, exclusive, autodelete queue random name
    String queueName = channel.queueDeclare().getQueue();
    // We bind the queue we just created to our fanout exchange
    channel.queueBind(queueName, EXCHANGE_NAME, "");

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
      System.out.println(" [x] Received '" + message + "'");
    };
    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
    });
  }
}