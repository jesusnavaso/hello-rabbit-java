# HELLO RABBIT!
This sample mini project is the equivalent to "Hello World!" for RabbitMQ and Java.
I am following the official documentation guide:

[tutorial-one-java](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)

For sending messages just run the class "Consumer.java". It will add one message to the specified queue each time you run it.

In order to consume those messages, run "Producer.java"

**NOTE:** I used this project just to get to know RabbitMQ a little bit. 
Both the quality of the code and how clean the POM is, don't matter that much. 

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

**NOTE: try to reuse this container as much as possible.** The effect of following commands will only persist as long as you keep 
on using the same container.

To be able to see the message, you will need `rabbitmqadmin`, and for that you will need `python` and `curl`:
```bash
apt update
apt install python3 curl
```
Enable rabbitmq management plugin:
```bash
rabbitmq-plugins enable rabbitmq_management
rabbitmq-plugins list
```

Download rabbitmqadmin from the local node and put it inside a folder that already in your path. Give it execution rights:
```bash
curl http://localhost:15672/cli/rabbitmqadmin -o /usr/local/bin/rabbitmqadmin
chmod +x /usr/local/bin/rabbitmqadmin
```

Now you can see the messages just by looking for your queue:
```bash
rabbitmqadmin get queue=<your_queue_name>
```

Enable bash autocompletion for rabbitmqadmin:
```bash
echo "source <(rabbitmqadmin --bash-completion)" >> /root/.bashrc && source /root/.bashrc
```

You can also open the GUI in your browser, where you can do virtually anything that you can also do using the CLI:
http://localhost:15672
