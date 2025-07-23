package com.Springboot.restApi.Payload;
 
import java.util.Set;

import lombok.Data;
import com.Springboot.restApi.Payload.CommentDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
 
@Data
public class PostDto {
	
	
	
	private long id;
	//title should not be nul or empty
		// itle shoud be min 2 value.
	@NotEmpty
	@Size(min=2,message="Post title should be atleast 2 character")
	private String title;
	@NotEmpty
	@Size(min=10,message="Post description should be atleast 10 character")
	private String description;
	
	@NotEmpty(message="Post Contnt should bot be empty")
	private String content;
	
	private Set<CommentDto> comments;
	 
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "PostDto [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", comments=" + comments + "]";
	}
	 
	
 
}