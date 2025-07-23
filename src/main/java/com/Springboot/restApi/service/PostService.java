package com.Springboot.restApi.service;
 
 
 
import java.util.List;

import com.Springboot.restApi.Payload.PostDto;
import com.Springboot.restApi.Payload.PostResponse;
 
 
public interface PostService {
 
	PostDto createPost(PostDto postDto);
	List<PostDto> getAllPost(int pageNo,int pageSize);
	PostResponse getAllPosts(int pageNo,int pageSize);
	PostResponse getAllPostSort(int pageNo,int pageSize,String sortBy);
	PostResponse getAllPostSortDir(int pageNo,int pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Long id);
	PostDto updatePost(PostDto postDto, Long id);
	void  deletePostById(Long id);
}