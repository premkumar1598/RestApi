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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.Springboot.restApi.Payload.PostDto;
import com.Springboot.restApi.Payload.PostResponse;
import com.Springboot.restApi.service.PostService;
import com.Springboot.restApi.utils.AppConst;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;
 
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<PostDto> createPost( @Valid @RequestBody PostDto postDto)
	{
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PostDto> getAllPost(
			@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
			@RequestParam(value="pageSize", defaultValue = "10",required = false)int pageSize)
	{
		return postService.getAllPost(pageNo,pageSize);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id)
	{
		return   ResponseEntity.ok(postService.getPostById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id") long id)
	{
		return new ResponseEntity(postService.updatePost(postDto, id), HttpStatus.OK);
	}
 
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable(name="id") long id)
	{
	 postService.deletePostById(id);
	 return new ResponseEntity<>("post entity deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo",defaultValue = AppConst.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
			@RequestParam(value="pageSize", defaultValue = AppConst.DEFAULT_PAGE_SIZE,required = false)int pageSize)
	{
		return postService.getAllPosts(pageNo,pageSize);
	}
	
	@GetMapping("/sort")
	public PostResponse getAllPostSort(
			@RequestParam(value="pageNo",defaultValue = AppConst.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
			@RequestParam(value="pageSize", defaultValue = AppConst.DEFAULT_PAGE_SIZE,required = false)int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConst.DEFAULT_SORT_BY,required = false)String sortBy)
	{
		return postService.getAllPostSort(pageNo,pageSize,sortBy);
	}
	@GetMapping("/sortDir")
	public PostResponse getAllPostSortDir(
			@RequestParam(value="pageNo",defaultValue = AppConst.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
			@RequestParam(value="pageSize", defaultValue = AppConst.DEFAULT_PAGE_SIZE,required = false)int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConst.DEFAULT_SORT_BY,required = false)String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConst.DEFAULT_SORT_DIRECTION,required = false) String sortDir)
	{
		return postService.getAllPostSortDir(pageNo,pageSize,sortBy,sortDir);
	}
}