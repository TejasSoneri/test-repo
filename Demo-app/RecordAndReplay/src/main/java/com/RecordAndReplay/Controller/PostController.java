package com.RecordAndReplay.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RecordAndReplay.Entity.Post;
import com.RecordAndReplay.Service.PostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {
	
	@Autowired
	private PostService postService;
	 
	@PostMapping("/createNewPost")
	public ResponseEntity<?> createPost(@RequestBody Post post) {		
		log.info("-----------------calling createNewPost Api-------------------");		
		return this.postService.createPost(post);
	}
	
}
