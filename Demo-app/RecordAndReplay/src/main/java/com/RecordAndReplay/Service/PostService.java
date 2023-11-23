package com.RecordAndReplay.Service;

import org.springframework.http.ResponseEntity;

import com.RecordAndReplay.Entity.Post;

public interface PostService {

	public ResponseEntity<?> createPost(Post post);
}
