package com.hcltech.fundtransfer.controllers;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.hcltech.fundtransfer.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AccountController.class)
class AccountControllerIT {

	@LocalServerPort
	private int port;

	private String jsonAccount;
	private String jsonTransfer;
	private String jsonLogin;
	private String token;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		jsonAccount = ResourceUtils.getContentFromResource("/json/account.json");
		jsonTransfer = ResourceUtils.getContentFromResource("/json/transfer.json");
		jsonLogin = ResourceUtils.getContentFromResource("/json/login.json");
		
		Response response = given().header("Content-Type", "application/json")

				.body(jsonLogin)

				.when().post("/auth/login");

		response.prettyPrint();

		token = response.body().jsonPath().getString("token");
		
	}

	@Test
	void shouldReturnStatus200_whenFindAccountsResume() {
		given().accept(ContentType.JSON).header("Authorization", "Bearer " + token).when().get("/accounts/resume").then().statusCode(HttpStatus.OK.value());
	}

	@Test
	void shouldReturnStatus200_whenFindAllAccountsResumeByCustomer() {
		given().pathParam("iban", "ASDASD12389172381").accept(ContentType.JSON).header("Authorization", "Bearer " + token).when().get("/accounts/{iban}").then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void shouldReturnStatus200_whenFindAllTransactionsByIban() {
		given().pathParam("iban", "ASDASD12389172381").accept(ContentType.JSON).header("Authorization", "Bearer " + token).when().get("/accounts/{iban}/transactions")
				.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	void shouldReturnStatus200_whenFindByIbanAndTransactionId() {
		given().pathParam("iban", "ASDASD12389172381").pathParam("id", "1").accept(ContentType.JSON).header("Authorization", "Bearer " + token).when()
				.get("/accounts/{iban}/transaction/{id}").then().statusCode(HttpStatus.OK.value());
	}

	@Test
	void shouldReturnStatus201_whenCreate() {
		given().body(jsonAccount).contentType(ContentType.JSON).accept(ContentType.JSON).header("Authorization", "Bearer " + token).when().post("/accounts").then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	void shouldReturnStatus200_whenTransfer() {
		given().body(jsonTransfer).contentType(ContentType.JSON).accept(ContentType.JSON).header("Authorization", "Bearer " + token).when().patch("/accounts/transfer").then()
				.statusCode(HttpStatus.OK.value());
	}

}
