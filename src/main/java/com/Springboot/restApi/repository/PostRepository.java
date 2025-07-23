package com.Springboot.restApi.repository;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Springboot.restApi.entity.*;

@Repository
public interface PostRepository extends JpaRepository<Post , Long> {
 
//	@Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = :id")
//	Optional<Post> findByIdWithComments(@Param("id") Long id);
}
 