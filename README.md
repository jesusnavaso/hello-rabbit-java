# HELLO RABBIT!

This sample mini project is the result of me partially following the Java Tutorials of the official RabbitMQ
documentation:
[tutorial-one-java](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)

The original code is [available in Github](https://github.com/rabbitmq/rabbitmq-tutorials)

The order to go through the lessons is supposed to be:

1. Hello World!
2. Work queues
3. Publish/Subscribe
4. Routing
5. Topics

**NOTE:** I used this project just to get to know RabbitMQ a little.
Both the quality of the code and how clean the POM is, don't matter that much.

## NOTES

Other useful resources:

- [management CLI](https://www.rabbitmq.com/management-cli.html)
- [Java api documentation guide](https://www.rabbitmq.com/api-guide.html)

It can be helpful to remember the default RabbitMQ configuration for a node running locally (extracted from the official
documentation)
![RabbitMQ default config](src/main/resources/rabbitmq_default_config.png)

You can also use URIs for connection, which have the following format
```amqp://userName:password@hostName:portNumber/virtualHost```

## Container setup

Pull the rabbitmq image, and run it, exposing the necessary ports. Feel free to use the latest version

```bash
docker run -it --name myRabbit -p 5672:5672 -p 15672:15672 rabbitmq:latest
```

This command will allow you to attach to the container interactively, and you will be able to see the logs.
If you press <Ctrl + c> you will go back to your terminal but the container will also stop.
You can add the option ``-d`` if you want to run it in the background.

If you want to start the container again in the future:

```bash
docker start myRabbit
```

And if you want to attach to the container (which will let you see the logs), just run:

```bash
docker attach myRabbit
```

To access a shell inside the container:

```bash
docker exec -it myRabbit /bin/bash
```

Once inside the container, you can already list the queues:

```bash
rabbitmqctl list_queues
```

**NOTE: try to reuse this container as much as possible.** The effect of following commands will only persist
as long as you keep using the same container.

To be able to see the messages, you will need `rabbitmqadmin`, and for that you will need `python` and `curl`.
Therefore, while still inside the container, run:

```bash
apt update
apt install python3 curl
```

Enable rabbitmq management plugin:

```bash
rabbitmq-plugins enable rabbitmq_management
rabbitmq-plugins list
```

Download rabbitmqadmin from the local node and put it inside a folder that is already in your path.
Give it execution permissions:

```bash
curl http://localhost:15672/cli/rabbitmqadmin -o /usr/local/bin/rabbitmqadmin
chmod +x /usr/local/bin/rabbitmqadmin
```

Now you can see the messages just by looking for your queue:

```bash
rabbitmqadmin get queue=<your_queue_name>
```

(OPTIONAL) Enable bash autocompletion for rabbitmqadmin:

```bash
echo "source <(rabbitmqadmin --bash-completion)" >> /root/.bashrc && source /root/.bashrc
```

You can also open the GUI in your browser, where you can do virtually anything that you can also do using the CLI:
http://localhost:15672.
You can login using the credentials:

```
username: guest
password: guest
```