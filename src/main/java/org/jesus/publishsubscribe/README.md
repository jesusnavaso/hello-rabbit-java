# TUTORIAL 3: PUBLISH / SUBSCRIBE

The producer never sends any messages directly to a queue.
Producers can only send messages to an **exchange**.

An exchange is a very simple thing. On one side it receives messages
from producers and the other side it pushes them to queues. The exchange must know exactly what to do with a message it
receives. Should it be appended to a particular queue? Should it be appended to many queues? Or should it get discarded.
The rules for that are defined by the **exchange type**.

Here are a few exchange types available: `direct`, `topic`, `headers` and `fanout`. We'll use `fanout`

```java
channel.exchangeDeclare("logs","fanout");
```

The `fanout` just broadcasts all the messages it receives to all the queues it knows.

The `routing_key` (of a message) and the `binding_key` (of a queue) are used by exchanges to route messages into queues:
When a new message with routing key pdf_create arrives at the direct exchange, the exchange routes it to the queue
where the `binding_key = routing_key

With `fanout`, all messages made a publisher to the exchange, will get broadcast to all consumers
that are subscribed to thart exchange

## TEMPORARY QUEUE

When we supply no parameters to `queueDeclare()` we create a non-durable, exclusive, autodelete queue with a generated
name

```java
String queueName=channel.queueDeclare().getQueue();
```

List exchanges:

```bash
rabbitmqctl list_exchanges
```

List bindings:

```bash
rabbitmqctl list_bindings
```

NOTE: From this example onwards, I have removed the explicit declaration of the host:
```factory.setHost("localhost")```
since it's set to _localhost_ by default