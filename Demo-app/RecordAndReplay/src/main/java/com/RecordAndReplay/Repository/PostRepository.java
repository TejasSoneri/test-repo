package com.RecordAndReplay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RecordAndReplay.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
