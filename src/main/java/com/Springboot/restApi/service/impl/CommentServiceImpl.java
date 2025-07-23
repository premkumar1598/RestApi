package com.Springboot.restApi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Springboot.restApi.Payload.CommentDto;
import com.Springboot.restApi.entity.Comment;
import com.Springboot.restApi.entity.Post;
import com.Springboot.restApi.exception.ResourceNotFoundException;
import com.Springboot.restApi.exception.RestApiException;
import com.Springboot.restApi.repository.CommentRepository;
import com.Springboot.restApi.repository.PostRepository;
import com.Springboot.restApi.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;
	

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper mapper ) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper=mapper;
	}


	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
 
		Comment comment = maptoEntity(commentDto);
		//Fetch by post  id
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		// set post id data to comment set post
		comment.setPost(post);
		// saved
		Comment newCom =commentRepository.save(comment);
		
		return maptoDto(newCom);
		
 
	}
	
	
	
	public CommentDto maptoDto(Comment comment)
	{
		CommentDto commentDto=mapper.map(comment, CommentDto.class);
//		
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
//		
		return commentDto;
	}
	
	public Comment maptoEntity(CommentDto commentDto)
	{
		Comment comment = mapper.map(commentDto, Comment.class);
//		Comment comment= new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
//		
		return comment;
	}


	@Override
	public List<CommentDto> getCommentByPostId(long PostId) {
		
		
	 List<Comment> comments =commentRepository.findByPostId(PostId);
	 return comments.stream().map(com->maptoDto(com)).collect(Collectors.toList());
	}


	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		 
		Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())){
			throw new RestApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");}
		
		return maptoDto(comment);
	}


	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
		Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())){
			throw new RestApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");}
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		
		Comment newCom = commentRepository.save(comment);
		
		 return maptoDto(newCom);
	}


	@Override
	public void deleteComment(long postId, long commentId) {
		Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())){
			throw new RestApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");}
		
		commentRepository.delete(comment);
	 
	}


	 
	
	
	
	
	
	
	
	
	
	
	
	
}
