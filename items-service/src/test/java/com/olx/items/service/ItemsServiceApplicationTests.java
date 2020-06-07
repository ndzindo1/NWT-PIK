package com.olx.items.service;

//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class ItemsServiceApplicationTests {

	//@Autowired
    //private MockMvc mvc;
	
	//@Order(0)
	@Test
	void contextLoads() {
	}
	/*
	 * @Order(0)
	 * 
	 * @Test public void addUserTest() throws Exception { String json =
	 * "{\"id\":1,\"firstName\":\"Ime\",\"lastName\":\"Prezime\",\"email\":\"ime@email.com\"}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/items/users/add")
	 * .contentType(MediaType.APPLICATION_JSON) .content(json))
	 * .andExpect(status().isOk());
	 * 
	 * String json2 =
	 * "{\"id\":2,\"firstName\":\"Ime\",\"lastName\":\"Prezime\",\"email\":\"ime2@email.com\"}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/items/users/add")
	 * .contentType(MediaType.APPLICATION_JSON) .content(json2))
	 * .andExpect(status().isOk());
	 * 
	 * }
	 * 
	 * @Order(1)
	 * 
	 * @Test public void deleteUserTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.delete("/olx/items/users/ime2@email.com"))
	 * .andExpect(status().isOk()); }
	 * 
	 * @Order(1)
	 * 
	 * @Test public void addCategoryTest() throws Exception { String json =
	 * "{\"name\":\"vozila\"}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/category/add")
	 * .contentType(MediaType.APPLICATION_JSON) .content(json))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$.name", is("vozila")));
	 * 
	 * String json2 = "{\"name\":\"kategorija za obrisati\"}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/category/add")
	 * .contentType(MediaType.APPLICATION_JSON) .content(json2))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$.name",
	 * is("kategorija za obrisati")));
	 * 
	 * }
	 * 
	 * @Order(1)
	 * 
	 * @Test public void addCategoryErrorTest() throws Exception { String json =
	 * "{\"name\":\"\"}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/category/add")
	 * .contentType(MediaType.APPLICATION_JSON) .content(json))
	 * .andExpect(status().isBadRequest());
	 * 
	 * }
	 * 
	 * @Order(2)
	 * 
	 * @Test public void getAllCategoryTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/category/all"))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$").isArray())
	 * .andExpect(jsonPath("$.[0].name", is("vozila"))); }
	 * 
	 * @Order(2)
	 * 
	 * @Test public void getCategoryByIdTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/category/1"))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$.name", is("vozila"))); }
	 * 
	 * @Order(2)
	 * 
	 * @Test public void getCategoryByIdErrorTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/category/20"))
	 * .andExpect(status().isBadRequest()); }
	 * 
	 * @Order(3)
	 * 
	 * @Test public void deleteCategoryByIdTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.delete("/olx/category/2"))
	 * .andExpect(status().isOk()); }
	 * 
	 * @Order(3)
	 * 
	 * @Test public void deleteCategoryByIdErrorTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.delete("/olx/category/20"))
	 * .andExpect(status().isBadRequest()); }
	 * 
	 * @Order(4)
	 * 
	 * @Test public void addProductTest() throws Exception { String json =
	 * "{\"arhived\":true,\"createdAt\":0,\"description\":\"opis\",\"location\":\"lokacija\",\"name\":\"produkt1\",\"preOwned\":true,\"price\":200,\"status\":true}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/products/add") .param("userId",
	 * "1") .param("categoryId", "1") .contentType(MediaType.APPLICATION_JSON)
	 * .content(json)) .andExpect(status().isOk()) .andExpect(jsonPath("$.name",
	 * is("produkt1"))) .andExpect(jsonPath("$.category.name", is("vozila")))
	 * .andExpect(jsonPath("$.user.firstName", is("Ime")));
	 * 
	 * String json2 =
	 * "{\"arhived\":true,\"createdAt\":0,\"description\":\"opis\",\"location\":\"lokacija\",\"name\":\"produkt2\",\"preOwned\":true,\"price\":200,\"status\":true}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/products/add") .param("userId",
	 * "1") .param("categoryId", "1") .contentType(MediaType.APPLICATION_JSON)
	 * .content(json2)) .andExpect(status().isOk()) .andExpect(jsonPath("$.name",
	 * is("produkt2"))) .andExpect(jsonPath("$.category.name", is("vozila")))
	 * .andExpect(jsonPath("$.user.firstName", is("Ime")));
	 * 
	 * String json3 =
	 * "{\"arhived\":false,\"createdAt\":0,\"description\":\"opis\",\"location\":\"lokacija\",\"name\":\"produkt3\",\"preOwned\":true,\"price\":200,\"status\":true}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/products/add") .param("userId",
	 * "1") .param("categoryId", "1") .contentType(MediaType.APPLICATION_JSON)
	 * .content(json3)) .andExpect(status().isOk()) .andExpect(jsonPath("$.name",
	 * is("produkt3"))) .andExpect(jsonPath("$.category.name", is("vozila")))
	 * .andExpect(jsonPath("$.user.firstName", is("Ime")));
	 * 
	 * String json4 =
	 * "{\"arhived\":false,\"createdAt\":0,\"description\":\"opis\",\"location\":\"lokacija\",\"name\":\"produkt4\",\"preOwned\":true,\"price\":200,\"status\":true}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/products/add") .param("userId",
	 * "1") .param("categoryId", "1") .contentType(MediaType.APPLICATION_JSON)
	 * .content(json4)) .andExpect(status().isOk()) .andExpect(jsonPath("$.name",
	 * is("produkt4"))) .andExpect(jsonPath("$.category.name", is("vozila")))
	 * .andExpect(jsonPath("$.user.firstName", is("Ime"))); }
	 * 
	 * @Order(4)
	 * 
	 * @Test public void addProductErrorTest() throws Exception { String json4 =
	 * "{\"arhived\":false,\"createdAt\":0,\"description\":\"opis\",\"location\":\"lokacija\",\"name\":\"produkt4\",\"preOwned\":true,\"price\":200,\"status\":true}";
	 * mvc.perform(MockMvcRequestBuilders.post("/olx/products/add") .param("userId",
	 * "1") .param("categoryId", "20") .contentType(MediaType.APPLICATION_JSON)
	 * .content(json4)) .andExpect(status().isBadRequest()); }
	 * 
	 * @Order(5)
	 * 
	 * @Test public void updateProductTest() throws Exception { String json =
	 * "{\"location\":\"novalokacija\"}";
	 * mvc.perform(MockMvcRequestBuilders.put("/olx/products/3")
	 * .contentType(MediaType.APPLICATION_JSON) .content(json))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$.location",
	 * is("novalokacija"))); }
	 * 
	 * @Order(5)
	 * 
	 * @Test public void updateProductErrorTest() throws Exception { String json =
	 * "{\"location\":\"\"}";
	 * mvc.perform(MockMvcRequestBuilders.put("/olx/products/3")
	 * .contentType(MediaType.APPLICATION_JSON) .content(json))
	 * .andExpect(status().isBadRequest()); }
	 * 
	 * @Order(5)
	 * 
	 * @Test public void searchTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/products/search") .param("name",
	 * "produkt")) .andExpect(status().isOk()) .andExpect(jsonPath("$.[0].name",
	 * is("produkt1"))) .andExpect(jsonPath("$.[1].name", is("produkt2")))
	 * .andExpect(jsonPath("$.[2].name", is("produkt3")))
	 * .andExpect(jsonPath("$.[3].name", is("produkt4"))); }
	 * 
	 * @Order(5)
	 * 
	 * @Test public void activeProductsTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/products/my/active")
	 * .param("id", "1")) .andExpect(status().isOk())
	 * .andExpect(jsonPath("$.[0].name", is("produkt3")))
	 * .andExpect(jsonPath("$.[1].name", is("produkt4"))); }
	 * 
	 * @Order(5)
	 * 
	 * @Test public void arhivedProductsTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/products/my/arhived")
	 * .param("id", "1")) .andExpect(status().isOk())
	 * .andExpect(jsonPath("$.[0].name", is("produkt1")))
	 * .andExpect(jsonPath("$.[1].name", is("produkt2"))); }
	 * 
	 * @Order(5)
	 * 
	 * @Test public void getProductByIdTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/products/3"))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$.name", is("produkt1"))); }
	 * 
	 * @Order(5)
	 * 
	 * @Test public void getProductByIdErrorTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.get("/olx/products/20"))
	 * .andExpect(status().isBadRequest()); }
	 * 
	 * @Order(6)
	 * 
	 * @Test public void deleteProductByIdTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.delete("/olx/products/3"))
	 * .andExpect(status().isOk()); }
	 * 
	 * @Order(6)
	 * 
	 * @Test public void deleteProductByIdErrorTest() throws Exception {
	 * mvc.perform(MockMvcRequestBuilders.delete("/olx/products/20"))
	 * .andExpect(status().isBadRequest()); }
	 */
}
