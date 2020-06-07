package com.olx.transaction.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.olx.transaction.service.models.User;
import com.olx.transaction.service.businesslogic.UserManager;

@Component
public class RabbitMQConsumer {
	
	@Value("${direct.exchange.dead.letter}")
	private String directExchangeDeadLetter;
	
	private final String routingkey = "bloola";
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = "${queue.name.transaction}")
	public void addUser(User user) {
		
		try {
			//Dodaj usera
			userManager.add(user);
		}catch(Exception e) {
			//Saljemo poruku user service da se desila greska
			rabbitTemplate.convertAndSend(directExchangeDeadLetter, routingkey, user);
		}
	}
	
	@RabbitListener(queues = "${queue.name.transaction.delete}")
	public void deleteUser(String email) {
		//Desila se greska u nekom servisu, brisemo usera ako ima
		if(userManager.getUserByEmail(email) != null) {
			userManager.delete(email);
		}
		System.out.println("Pokrenuta inverza akcija - transaction!");
	}
}
