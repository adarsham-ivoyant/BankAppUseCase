package com.example.BankSubModel.JMS;

import com.example.BankSubModel.repository.Repo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    public Repo repo;
    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {

         repo.save(message);
       // System.out.println(message);
    }
}
