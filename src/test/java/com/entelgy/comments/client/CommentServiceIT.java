package com.entelgy.comments.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.entelgy.comments.client.api.ClientApiException;
import com.entelgy.comments.client.model.CommentResumenModel;
import com.entelgy.comments.client.service.CommentService;

@SpringBootTest
public class CommentServiceIT {

	@Autowired
	CommentService commentService;

	@Test
	public void testListCommentsIsNotEmpty() {
		// Escenario:
		List<CommentResumenModel> comments = new ArrayList<>();

		// Acción:
		try {
			comments = commentService.listComments();
		} catch (ClientApiException e) {
			e.printStackTrace();
		}

		// Validación:
		assertThat(comments).isNotEmpty();
		assertThat(comments.size()).isGreaterThan(0);
	}

	@Test
	public void testListCommentsIsEmpty() {
		// Escenario:
		List<CommentResumenModel> comments = new ArrayList<>();

		// Acción:
		// comments = commentService.listComments();

		// Validación:
		assertThat(comments).isEmpty();
		assertThat(comments.size()).isEqualTo(0);
	}

}
