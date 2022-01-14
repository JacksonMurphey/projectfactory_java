package com.jmurphey.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jmurphey.exam.models.Idea;
import com.jmurphey.exam.models.User;
import com.jmurphey.exam.repositories.IdeaRepository;




@Service
public class IdeaService {
	
	@Autowired
	private IdeaRepository ideaRepo;
	
	
// --- Find By Id ---
	
	public Idea getIdeaById(Long id) {
		return ideaRepo.findById(id).orElse(null);
	}
	
	
// --- Find All ---
	
	public List<Idea> allIdeas(){
		return ideaRepo.findAll();
	}
	
	public Idea getIdeaCreatorAndVistorCount(Long id){
		return ideaRepo.findIdeaCreatorAndVistorCount(id);
	}

// ---- Create/Save/Update Idea ---
	
	public Idea createIdea(Idea idea) {
		return ideaRepo.save(idea);
	}
	
	public Idea updateIdea(Idea idea) {
		return ideaRepo.save(idea);
	}
	
	
// --- Delete Idea ---
	
	public void deleteIdea(Long id) {
		ideaRepo.deleteById(id);
	}
	
	
// --- Add/Create Relationship ---
	
	public void addToUsersLiked(User user, Idea idea) {
		List<User> likedUsers = idea.getUsersLiked();
		likedUsers.add(user);
		ideaRepo.save(idea);
		
	}
	
	public void removeUserLike(User user, Idea idea) {
		idea.getUsersLiked().remove(user);
		ideaRepo.save(idea);
	}
}
