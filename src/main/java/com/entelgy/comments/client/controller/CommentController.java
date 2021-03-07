package com.entelgy.comments.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entelgy.comments.client.model.CommentResumenModel;
import com.entelgy.comments.client.service.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CommentResumenModel> responseComment() {

		return commentService.listComments();

	}

}
