package com.jmurphey.exam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jmurphey.exam.models.Idea;


@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long>{

	List<Idea> findAll();
	

	@Query(value="SELECT COUNT(user_id) FROM likers where idea_id = ?1", nativeQuery = true)
	int getVisitorCount(Long id);
	
	
//	@Query(value = "SELECT COUNT(user_id) FROM likers", nativeQuery = true)
	@Query(value = "SELECT ideas.name AS \"Name\", CONCAT(users.first_name, \" \", users.last_name) as \"creator name\", COUNT(likers.user_id) as \"Total Likes\" FROM likers \n"
			+ "Join ideas on likers.idea_id = ideas.id\n"
			+ "Join users on ideas.user_id = users.id\n"
			+ "Where idea_id = ?1", nativeQuery = true )
	Idea findIdeaCreatorAndVistorCount(Long id);
	
	
	
//	@Query(value = "SELECT * FROM ideas "
//			+ "JOIN likers ON likers.idea_id = ideas.id "
//			+ "JOIN users On likers.user_id = users.id"
//			+ "ORDER BY ideas.likers.user_id DESC", nativeQuery = true)
//	List<Idea> findAllByOrderByusersLikedDesc();

}


//@Query(SELECT a FROM albums a ORDER BY a.likers.size DESC)
//List<Album> findAllByOrderByAllLikesDesc();