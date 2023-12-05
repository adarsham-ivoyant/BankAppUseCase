package com.example.BankApplicationUseCase.JMS;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    public RabbitTemplate template;

    @PostMapping("/adddingMoneytoFD")
    public String publishMessage(@RequestBody CustomMessage message){
        if(message.getMoney()>50000){
            message.setMessageId(UUID.randomUUID().toString());
            message.setMessageDate(new Date());
            template.convertAndSend(MQConfig.EXCHANGE,
                    MQConfig.ROUTINGKEY,message);

            return "Message published";
        }
       else{
            System.out.println("cant be able to add");
        }
       return null;
    }
}
