package com.entelgy.comments.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entelgy.comments.client.api.ClientApiException;
import com.entelgy.comments.client.api.CommentClient;
import com.entelgy.comments.client.model.CommentResumenModel;

@Service
public class CommentService {
	
	@Value("${entelgy.comments.host-service}")
	private String host;

	public List<CommentResumenModel> listComments() {
		RestTemplate restTemplate = new RestTemplate();

		CommentClient commentClient = new CommentClient(restTemplate, host);

		try {
			return commentClient.listComments();
		} catch (ClientApiException e) {
			e.printStackTrace();

			/*if (e.getProblem() != null) {
				// System.out.println(e.getProblem());
				System.out.println(e.getProblem().getUserMessage());
				// System.out.println(e.getProblem().getStatus());

				e.getProblem().getObjects().stream().forEach(p -> System.out.println("- " + p.getUserMessage()));

			} else {
				System.out.println("Error desconocido");
				e.printStackTrace();
			}*/
		}
		return null;
	}	

}
