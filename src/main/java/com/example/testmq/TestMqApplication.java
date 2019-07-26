package com.example.testmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableBinding(Sink.class)
public class TestMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestMqApplication.class, args);
	}


	@StreamListener(value = Sink.first_listener, condition = "headers['type']=='first'")
	public void listener_one(Message<String> msg)
    {
        System.out.println("first message " + msg);
    }

    @StreamListener(value = Sink.second_listener, condition = "headers['type']=='second'")
    public void listener_two(Message<String> msg)
    {

        System.out.println("second message " + msg);
    }
}


interface Sink
{
    String first_listener = "first_listener";

    String second_listener = "second_listener";

    @Input(first_listener)
    SubscribableChannel first_listener();


    @Input(second_listener)
    SubscribableChannel second_listener();
}
