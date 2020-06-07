package com.olx.user.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.olx.user.service.businesslogic.UserManager;
import com.olx.user.service.models.User;


@Component
public class RabbitMQSender {
	
	@Value("${fanout.exchange}")
	private String fanoutExchange;
	
	@Value("${fanout.exchange.delete}")
	private String fanoutExchangeDelete;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	
	public void saveUser(User user){
		rabbitTemplate.setExchange(fanoutExchange);
		rabbitTemplate.convertAndSend(user);
	}
	
	@RabbitListener(queues = "${queue.name.dead.letter}")
	public void deleteUser(User user){
		if(userManager.getUserByEmail(user.getEmail()) != null) {
			userManager.delete(user.getId());
			rabbitTemplate.setExchange(fanoutExchangeDelete);
			rabbitTemplate.convertAndSend(user.getEmail());
			System.out.println("Pokrenuta inverza akcija!");	
		}
	}
}
