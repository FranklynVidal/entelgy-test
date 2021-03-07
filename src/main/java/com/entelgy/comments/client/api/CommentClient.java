package com.entelgy.comments.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.entelgy.comments.client.model.CommentResumenModel;
import com.entelgy.comments.client.util.Constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentClient {
	
	private RestTemplate restTemplate;
	private String url;

	public List<CommentResumenModel> listComments() {
		try {

			URI resourceUri = URI.create(url + Constant.RESOURCE_PATH);

			CommentResumenModel[] comments = restTemplate.getForObject(resourceUri, CommentResumenModel[].class);

			return Arrays.asList(comments);

		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}	

}
