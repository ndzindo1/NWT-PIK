package com.olx.message.service.businesslogic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.olx.message.service.businesslogic.ChatManager;
import com.olx.message.service.models.Chat;
import com.olx.message.service.models.User;
import com.olx.message.service.repositories.ChatRepository;

@Component
public class DefaultChatManager implements ChatManager {
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public List<Chat> getMyChat(User user) {	
		return chatRepository.findByReceiver(user);
	}

	@Override
	public Chat getChatById(Long id) {
		return chatRepository.findById(id).orElse(null);
	}

	@Override
	public Chat save(Chat chat) {
		return chatRepository.save(chat);
	}

}
