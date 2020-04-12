package com.olx.message.service;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class MessageServiceApplicationTests {

	@Autowired
    private MockMvc mvc;
	
	@Order(0)
	@Test
	void contextLoads() {
	}
	
	@Order(0)
	@Test
	public void addUserTest() throws Exception
	{
		String json = "{\"id\":1,\"firstName\":\"Ime\",\"lastName\":\"Prezime\",\"email\":\"ime@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/messages/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk());

		String json2 = "{\"id\":2,\"firstName\":\"Ime2\",\"lastName\":\"Prezime2\",\"email\":\"ime2@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/messages/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json2))
		.andExpect(status().isOk());

		String json3 = "{\"id\":3,\"firstName\":\"Ime3\",\"lastName\":\"Prezime3\",\"email\":\"ime3@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/messages/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
		.andExpect(status().isOk());

		String json4 = "{\"id\":4,\"firstName\":\"Ime4\",\"lastName\":\"Prezime4\",\"email\":\"ime4@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/messages/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json4))
		.andExpect(status().isOk());
	}
	
	/*@Order(1)
	@Test
	public void deleteUserTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.delete("/olx/messages/users/ime2@email.com")).andExpect(status().isOk());	
	}*/
	
	@Order(1)
	@Test
	public void addChatTest() throws Exception
	{
		String json = "{\"body\":\"\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/chat/add")
				.param("receiverId", "1")
				.param("senderId", "2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.receiver.firstName", is("Ime")))
		.andExpect(jsonPath("$.sender.firstName", is("Ime2")));

		mvc.perform(MockMvcRequestBuilders.post("/olx/chat/add")
				.param("receiverId", "1")
				.param("senderId", "3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.receiver.firstName", is("Ime")))
		.andExpect(jsonPath("$.sender.firstName", is("Ime3")));
		
		mvc.perform(MockMvcRequestBuilders.post("/olx/chat/add")
				.param("receiverId", "1")
				.param("senderId", "4")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.receiver.firstName", is("Ime")))
		.andExpect(jsonPath("$.sender.firstName", is("Ime4")));
	}

	@Order(1)
	@Test
	public void addChatErrorTest() throws Exception
	{
		String json = "{\"body\":\"\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/chat/add")
				.param("receiverId", "1")
				.param("senderId", "55")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isBadRequest());
	}
	
	@Order(2)
	@Test
	public void getMyChatTest() throws Exception
	{
		String json = "{\"userId\":1}";
		mvc.perform(MockMvcRequestBuilders.get("/olx/chat/my")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].sender.firstName", is("Ime2")))
		.andExpect(jsonPath("$.[1].sender.firstName", is("Ime3")))
		.andExpect(jsonPath("$.[2].sender.firstName", is("Ime4")));
	}
	
	@Order(2)
	@Test
	public void getMyChatErrorTest() throws Exception
	{
		String json = "{\"userId\":55}";
		mvc.perform(MockMvcRequestBuilders.get("/olx/chat/my")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isBadRequest());
	}
	
	@Order(2)
	@Test
	public void addMassageTest() throws Exception
	{
		String json = "{\"body\":\"Zdravo\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/chat/1/message/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.messages[0].body", is("Zdravo")));
		
		String json2 = "{\"body\":\"Imam pitanje vezano za produkt1.\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/chat/1/message/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json2))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.messages[0].body", is("Zdravo")))
		.andExpect(jsonPath("$.messages[1].body", is("Imam pitanje vezano za produkt1.")));
	}
	
	@Order(2)
	@Test
	public void addMssageErrorTest() throws Exception
	{
		String json = "{\"body\":\"\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/chat/1/message/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isBadRequest());
	}
	
	@Order(3)
	@Test
	public void deleteChatByIdTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.delete("/olx/chat/1")).andExpect(status().isOk());
	}
	
	@Order(3)
	@Test
	public void deleteChatByIdErrorTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.delete("/olx/chat/20")).andExpect(status().isBadRequest());
	}
}
