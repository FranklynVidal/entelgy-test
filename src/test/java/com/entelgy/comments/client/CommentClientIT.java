package com.entelgy.comments.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

import com.entelgy.comments.client.api.ClientApiException;
import com.entelgy.comments.client.model.CommentResumenModel;
import com.entelgy.comments.client.util.Constant;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommentClientIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	private HttpEntity request;

	@Test
	public void listCommentsDefaultTests() throws Exception {

		ResponseEntity<CommentResumenModel[]> comments;

		try {
			comments = this.restTemplate.exchange(Constant.HOST + Constant.RESOURCE_PATH, 
												  HttpMethod.GET, 
												  request, 
												  CommentResumenModel[].class);
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}

		assertThat(comments).isNotNull();
	}

}
