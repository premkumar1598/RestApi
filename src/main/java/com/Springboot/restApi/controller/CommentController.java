package com.Springboot.restApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.restApi.Payload.CommentDto;
import com.Springboot.restApi.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("/post/{id}/comments")
	public ResponseEntity<CommentDto>createComment(  @PathVariable(value="id") long postId,
			@Valid @RequestBody CommentDto commentDto) {
		
		
		return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/post/{id}/comments")
	public List<CommentDto> getCommentByPostId (@PathVariable(value="id")long PostId)
	{
		return commentService.getCommentByPostId(PostId);
	}
	
	@GetMapping("/post/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentbyId(@PathVariable(value="postId")Long postId,
													@PathVariable(value="commentId")Long commentId){
	
	CommentDto commentDto=commentService.getCommentById(postId,commentId);
	return new ResponseEntity<>(commentDto,HttpStatus.OK); 

	}
	
	@PutMapping("/post/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(  @PathVariable(value="postId")Long postId,
			@PathVariable(value="commentId")Long commentId,
			@Valid @RequestBody CommentDto commentDto){

		CommentDto newComment=commentService.updateComment(postId,commentId,commentDto);
		return new ResponseEntity<>(newComment,HttpStatus.OK); 

}
	@DeleteMapping("/post/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="postId")Long postId,
			@PathVariable(value="commentId")Long commentId){

		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>( "entity deleted successfully ",HttpStatus.OK); 

}
}
