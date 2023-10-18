package com.nelioalves.workshopmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		try {
			Post post = postRepository.findById(id).get();
			return post;
		}catch(Exception e) {
			throw new ObjectNotFoundException("Error: Id post não encontrado!");
		}
	}
}
