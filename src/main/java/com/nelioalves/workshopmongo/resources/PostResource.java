package com.nelioalves.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/findByTitle")
	public ResponseEntity<List<Post>> findByTitleContaining(@RequestParam(value = "text", defaultValue="") String title){
		title = URL.decodeParam(title);
		List<Post> posts = postService.findByTitle(title);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue="") String title,
			@RequestParam(value = "minDate", defaultValue="") String minDate, 
			@RequestParam(value = "maxDate", defaultValue="") String maxDate){
		title = URL.decodeParam(title);
		Date min = URL.convertDate(minDate, new Date(0L));	
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> posts = postService.fullSearch(title, min, max);
		return ResponseEntity.ok().body(posts);
		
		
	}
	
}
