package com.olx.transaction.service;

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
class TransactionServiceApplicationTests {

	@Autowired
    private MockMvc mvc;
	
	@Order(0)
	@Test
	void contextLoads() {
	}
	
	@Order(1)
	@Test
	public void addTransactionTest() throws Exception
	{
		String json = "{\"owner\":1,\"buyer\":2,\"product\":1}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.owner.firstName", is("Ime1")))
		.andExpect(jsonPath("$.buyer.firstName", is("Ime2")))
		.andExpect(jsonPath("$.product.name", is("produkt1")));
		
		String json2 = "{\"owner\":1,\"buyer\":3,\"product\":2}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json2))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.owner.firstName", is("Ime1")))
		.andExpect(jsonPath("$.buyer.firstName", is("Ime3")))
		.andExpect(jsonPath("$.product.name", is("produkt2")));
		
		String json3 = "{\"owner\":1,\"buyer\":4,\"product\":3}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.owner.firstName", is("Ime1")))
		.andExpect(jsonPath("$.buyer.firstName", is("Ime4")))
		.andExpect(jsonPath("$.product.name", is("produkt3")));
		
		String json4 = "{\"owner\":1,\"buyer\":4,\"product\":4}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json4))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.owner.firstName", is("Ime1")))
		.andExpect(jsonPath("$.buyer.firstName", is("Ime4")))
		.andExpect(jsonPath("$.product.name", is("produkt4")));
		
	}
	
	/* Nepostojeci produkt */
	@Order(1)
	@Test
	public void addTransactionErrorTest() throws Exception
	{
		String json = "{\"owner\":1,\"buyer\":2,\"product\":44}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isBadRequest());		
	}
	
	@Order(2)
	@Test
	public void closeTransactionTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.put("/olx/transaction/1/close"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.closed", is(true)));	
		
		mvc.perform(MockMvcRequestBuilders.put("/olx/transaction/2/close"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.closed", is(true)));	
	}
	
	/*Id transakcije ne postoji*/
	@Order(2)
	@Test
	public void closeTransactionErrorTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.put("/olx/transaction/44/close"))
		.andExpect(status().isBadRequest());		
	}
	
	@Order(3)
	@Test
	public void myClosedTransactionTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/olx/transaction/1/closed"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].buyer.firstName", is("Ime2")))
		.andExpect(jsonPath("$.[1].buyer.firstName", is("Ime3")));
	}
	
	/*Ne postoji user sa tim id*/
	@Order(3)
	@Test
	public void myClosedTransactionErrorTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/olx/transaction/44/closed"))
		.andExpect(status().isBadRequest());		
	}
	
	@Order(3)
	@Test
	public void myActiveTransactionTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/olx/transaction/1/active"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].buyer.firstName", is("Ime4")))
		.andExpect(jsonPath("$.[1].buyer.firstName", is("Ime4")));
	}
	
	/*Ne postoji user sa tim id*/
	@Order(3)
	@Test
	public void myActiveTransactionErrorTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/olx/transaction/44/active"))
		.andExpect(status().isBadRequest());		
	}
	
	// Testiranje kontrolera za dodavanje usera i produkata 
	// Nije testiran pogresan unos jer je to urađeno kroz 
	// servise koji su zaduženi za to user-service i items-service
	
	@Order(0)
	@Test
	public void addProductTest() throws Exception
	{
		String json = "{\"id\":1,\"arhived\":true,\"name\":\"produkt1\",\"price\":200}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/products/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk());
		
		String json2 = "{\"id\":2,\"arhived\":true,\"name\":\"produkt2\",\"price\":200}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/products/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json2))
		.andExpect(status().isOk());
		
		String json3 = "{\"id\":3,\"arhived\":true,\"name\":\"produkt3\",\"price\":200}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/products/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
		.andExpect(status().isOk());
		
		String json4 = "{\"id\":4,\"arhived\":true,\"name\":\"produkt4\",\"price\":200}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/products/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json4))
		.andExpect(status().isOk());
	}
	
	@Order(0)
	@Test
	public void addUserTest() throws Exception
	{
		String json = "{\"id\":1,\"firstName\":\"Ime1\",\"lastName\":\"Prezime1\",\"email\":\"ime1@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk());

		String json2 = "{\"id\":2,\"firstName\":\"Ime2\",\"lastName\":\"Prezime2\",\"email\":\"ime2@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json2))
		.andExpect(status().isOk());
		
		String json3 = "{\"id\":3,\"firstName\":\"Ime3\",\"lastName\":\"Prezime3\",\"email\":\"ime3@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
		.andExpect(status().isOk());
		
		String json4 = "{\"id\":4,\"firstName\":\"Ime4\",\"lastName\":\"Prezime4\",\"email\":\"ime4@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json4))
		.andExpect(status().isOk());
		
		String json5 = "{\"id\":5,\"firstName\":\"Ime5\",\"lastName\":\"Prezime5\",\"email\":\"ime5@email.com\"}";
		mvc.perform(MockMvcRequestBuilders.post("/olx/transaction/users/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json5))
		.andExpect(status().isOk());
	}

	@Order(1)
	@Test
	public void deleteUserTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.delete("/olx/transaction/users/ime5@email.com"))
		.andExpect(status().isOk());	
	}
}
