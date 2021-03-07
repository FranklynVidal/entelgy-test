package com.entelgy.comments.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CommentResumenModel {

	private Long postId;
	private Long id;
	private String email;

}
