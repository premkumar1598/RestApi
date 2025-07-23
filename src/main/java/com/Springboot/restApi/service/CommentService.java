package com.Springboot.restApi.service;

import java.util.List;

import com.Springboot.restApi.Payload.CommentDto;


public interface CommentService {
	
	public CommentDto createComment(long post_id,CommentDto commentDto);

	List<CommentDto> getCommentByPostId(long PostId);
	
	public CommentDto getCommentById(long postId,long commentId);
	
	public CommentDto updateComment(long postId,long commentId,CommentDto commentDto);
	
	public void deleteComment(long postId,long commentId);
}
