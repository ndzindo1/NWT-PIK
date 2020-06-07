package com.olx.user.service.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig{
	
	//Time to live
	private Long ttl = 10000L;
	
	//Imena queue za upis
	@Value("${queue.name.auth}")
	private String queueNameAuth;

	@Value("${queue.name.item}")
	private String queueNameItem;

	@Value("${queue.name.message}")
	private String queueNameMessage;

	@Value("${queue.name.transaction}")
	private String queueNameTransaction;

	//Imena queue za inverzne akcije
	@Value("${queue.name.auth.delete}")
	private String queueNameAuthDelete;

	@Value("${queue.name.item.delete}")
	private String queueNameItemDelete;

	@Value("${queue.name.message.delete}")
	private String queueNameMessageDelete;

	@Value("${queue.name.transaction.delete}")
	private String queueNameTransactionDelete;

	@Value("${queue.name.dead.letter}")
	private String queueNameDeadLetter;

	//Imena exchange
	@Value("${fanout.exchange}")
	private String fanoutExchange;

	@Value("${fanout.exchange.delete}")
	private String fanoutExchangeDelete;

	@Value("${direct.exchange.dead.letter}")
	private String directExchangeDeadLetter;

	//Dead Letter Queue
	@Bean
	Queue dlq() {
		return QueueBuilder.nonDurable(queueNameDeadLetter).build();
	}

	//Dead Exchange	
	@Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange(directExchangeDeadLetter);
	}

	//Bind 'dead_queue' and 'dead_exchange'.
	@Bean
	Binding DLQbinding() {
		return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("bloola");
	}

	//Queue 
	@Bean
	Queue queueAuth() {
		return QueueBuilder.nonDurable(queueNameAuth).withArgument("x-dead-letter-exchange", directExchangeDeadLetter)
				.withArgument("x-dead-letter-routing-key", "bloola")
				.withArgument("x-message-ttl", ttl).build();
	}

	@Bean
	Queue queueItem() {
		return  QueueBuilder.nonDurable(queueNameItem).withArgument("x-dead-letter-exchange", directExchangeDeadLetter)
				.withArgument("x-dead-letter-routing-key", "bloola")
				.withArgument("x-message-ttl", ttl).build();
	}

	@Bean
	Queue queueMessage() {
		return  QueueBuilder.nonDurable(queueNameMessage).withArgument("x-dead-letter-exchange", directExchangeDeadLetter)
				.withArgument("x-dead-letter-routing-key", "bloola")
				.withArgument("x-message-ttl", ttl).build();
	}

	@Bean
	Queue queueTransaction() {
		return  QueueBuilder.nonDurable(queueNameTransaction).withArgument("x-dead-letter-exchange", directExchangeDeadLetter)
				.withArgument("x-dead-letter-routing-key", "bloola")
				.withArgument("x-message-ttl", ttl).build();
	}

	@Bean
	Queue queueAuthDelete() {
		return QueueBuilder.nonDurable(queueNameAuthDelete)
				.withArgument("x-message-ttl", ttl).build();
	}

	@Bean
	Queue queueItemDelete() {
		return QueueBuilder.nonDurable(queueNameItemDelete)
				.withArgument("x-message-ttl", ttl).build();
	}

	@Bean
	Queue queueMessageDelete() {
		return QueueBuilder.nonDurable(queueNameMessageDelete)
				.withArgument("x-message-ttl", ttl).build();
	}

	@Bean
	Queue queueTransactionDelete() {
		return QueueBuilder.nonDurable(queueNameTransactionDelete)
				.withArgument("x-message-ttl", ttl).build();
	}

	//Exchange
	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange(fanoutExchange);
	}

	@Bean
	FanoutExchange exchangeDelete() {
		return new FanoutExchange(fanoutExchangeDelete);
	}

	//Binding 
	@Bean
	Binding bindingAuth(Queue queueAuth, FanoutExchange exchange) {
		return BindingBuilder.bind(queueAuth).to(exchange);
	}

	@Bean
	Binding bindingItem(Queue queueItem, FanoutExchange exchange) {
		return BindingBuilder.bind(queueItem).to(exchange);
	}

	@Bean
	Binding bindingMassage(Queue queueMessage, FanoutExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange);
	}

	@Bean
	Binding bindingTransaction(Queue queueTransaction, FanoutExchange exchange) {
		return BindingBuilder.bind(queueTransaction).to(exchange);
	}

	//Binding delete
	@Bean
	Binding bindingAuthDelete(Queue queueAuthDelete, FanoutExchange exchangeDelete) {
		return BindingBuilder.bind(queueAuthDelete).to(exchangeDelete);
	}

	@Bean
	Binding bindingItemDelete(Queue queueItemDelete, FanoutExchange exchangeDelete) {
		return BindingBuilder.bind(queueItemDelete).to(exchangeDelete);
	}

	@Bean
	Binding bindingMassageDelete(Queue queueMessageDelete, FanoutExchange exchangeDelete) {
		return BindingBuilder.bind(queueMessageDelete).to(exchangeDelete);
	}

	@Bean
	Binding bindingTransactionDelete(Queue queueTransactionDelete, FanoutExchange exchangeDelete) {
		return BindingBuilder.bind(queueTransactionDelete).to(exchangeDelete);
	}

	//Message converter
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
