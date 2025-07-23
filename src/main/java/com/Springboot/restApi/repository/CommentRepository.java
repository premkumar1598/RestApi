package com.Springboot.restApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Springboot.restApi.entity.*;

//@Repository JPA repo is extends simple JPA repo and it extends @Repo and @transcational so we no need to extends the Repo.
public interface CommentRepository extends JpaRepository<Comment , Long> {
	
	List<Comment> findByPostId(long PostId);

}
