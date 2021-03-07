package com.entelgy.comments.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.entelgy.comments.client.controller.CommentController;
import com.entelgy.comments.client.util.Constant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerIT {
	
	@Autowired
	private CommentController commentController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(commentController).isNotNull();
	}

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = Constant.RESOURCE_PATH;
	}

	@Test
	public void returnStatus200_WhenGetComments() {

		RestAssured.given()
			.basePath("/comments")
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void returnSize500Comments_WhenGetComments() {

		RestAssured.given()
			.basePath("/comments")
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.body("",Matchers.hasSize(500));
	}	

}
