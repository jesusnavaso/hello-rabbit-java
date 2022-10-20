# TUTORIAL 4: ROUTING

A **binding** is a relationship between an **exchange** and a **queue**.
Bindings can take an extra routingKey parameter. To avoid the confusion with a basic_publish parameter
we're going to call it a binding key.
```channel.queueBind(queueName, EXCHANGE_NAME, "black");```
The meaning of a binding key depends on the exchange type.
The fanout exchanges, which we used previously, simply ignored its value.