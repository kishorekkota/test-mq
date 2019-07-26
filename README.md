# test-mq

This project run a spring boot app using gradle and listening to 
* rabbit mq on localhost running @ default port 
* with username=guest and password=guest

You can run rabbit mq using docker command.

`docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest -p 15672:15672 -p 5672:5672 rabbitmq:3-management`

This command is running a docker rabbit mq container with name `some-rabbit` enabling management plugin, which is needed for rabbit mq dashboard + exposing default port on the local PC by mapping container ports to local ports.

Simple application having two listeners on the same exchange with conditional based headers.

Send a message on the 'hello-exchange' in the rabbit mq consoler with header 'type=first' - it will be picked up by first listeners.

Send a message on the 'hello-exchange' in the rabbit mq consoler with header 'type=second' - it will be picked up by second listeners.


Actual binding enabled for.

for seeing listener status and binding

http://localhost:8080/actuator/bindings

For stopping listener - it works only with exchange name

curl -d '{"state":"STOPPED"}' -H "Content-Type: application/json" -X POST http://localhost:8080/actuator/bindings/hello-exchange


But with binding name it does not work at the moment.

curl -d '{"state":"STOPPED"}' -H "Content-Type: application/json" -X POST http://localhost:8080/actuator/bindings/second_listener
