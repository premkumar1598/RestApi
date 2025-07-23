package com.Springboot.restApi.service.impl;
 
 
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.Springboot.restApi.entity.Post;
import com.Springboot.restApi.Payload.PostDto;
import com.Springboot.restApi.Payload.PostResponse;
import com.Springboot.restApi.repository.PostRepository;
 
import com.Springboot.restApi.service.PostService;
import com.Springboot.restApi.exception.*;
 
 
@Service
public class PostServiceImpl implements PostService{
 
	
	private PostRepository postRepository;
	private ModelMapper mapper;
	// @autowired not needed if one constructor is in after 4.3 version.
	public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
		super();
		this.postRepository = postRepository;
		this.mapper=mapper;
	}
 
	@Override
	public PostDto createPost (PostDto postDto) {
		
		Post post = mapToEntity(postDto);
		Post newPost=postRepository.save(post);
		
		PostDto postResponse =  mapToDto(newPost);
 		return postResponse;
	}
	
	
	//convert Entity to DTO
	private PostDto mapToDto(Post post)
	{
		PostDto postDto=mapper.map(post,PostDto.class);
		
		 
//		PostDto postResponse = new PostDto();
//		postResponse.setId(post.getId());
//		postResponse.setTitle(post.getTitle());
//		postResponse.setDescription(post.getDescription());
//		postResponse.setContent(post.getContent());
//		
		return postDto;
		
	}
	
	//Convert DTO to Entity
	private Post mapToEntity(PostDto postDto)
	{
		Post post=mapper.map(postDto, Post.class);
//		
//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
//		
		return post;
	}

	@Override
	public List<PostDto> getAllPost(int pageNo,int pageSize) {
		
	 
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Post> posts = postRepository.findAll(pageable);
		
		// if Page is used we need to get content from pageable we need to use gettContent();
		List<Post>  listOfPage=posts.getContent();
 	 
		return listOfPage.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

	@Override
	public PostDto getPostById(Long id) {
		 Post post= postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		 
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
	 
		Post newPost=postRepository.save(post);
		
		 return mapToDto(newPost);
 		 
	}

	@Override
	public void   deletePostById(Long id) {
		Post post= postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		 postRepository.deleteById(id);
	 
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		Page<Post> posts = postRepository.findAll(pageable);
		
		// if Page is used we need to get content from pageable we need to use gettContent();
		List<Post>  listOfPage=posts.getContent();
		
		// connvert post to Dto
		List<PostDto> content = listOfPage.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
		//postDto mapping post reponse  and getting innfo for page no details. 
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setToatalPage(posts.getTotalPages());
		postResponse.setTotalElement(posts.getTotalElements());
		postResponse.setLast(posts.isLast()); 
		
		
		return postResponse;
 
	}

	@Override
	public PostResponse getAllPostSort(int pageNo, int pageSize, String sortBy) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Post> posts = postRepository.findAll(pageable);
		
		// if Page is used we need to get content from pageable we need to use gettContent();
		List<Post>  listOfPage=posts.getContent();
		
		// connvert post to Dto
		List<PostDto> content = listOfPage.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
		//postDto mapping post reponse  and getting innfo for page no details. 
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setToatalPage(posts.getTotalPages());
		postResponse.setTotalElement(posts.getTotalElements());
		postResponse.setLast(posts.isLast()); 
		return postResponse; 
	}

	@Override
	public PostResponse getAllPostSortDir(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
	 
		Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
		Page<Post>posts= postRepository.findAll(pageable);
		List<Post> listOfPosts=posts.getContent();
		List<PostDto> content=listOfPosts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setToatalPage(posts.getTotalPages());
		postResponse.setTotalElement(posts.getTotalElements());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}
	 
}