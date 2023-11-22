package com.RecordAndReplay.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.RecordAndReplay.Entity.Post;
import com.RecordAndReplay.Repository.PostRepository;
import com.RecordAndReplay.Response.PostResponse;
import com.RecordAndReplay.Service.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Value("${HTTP_OUTBOUND.URL}")
	private String url;

	@Override
	public ResponseEntity<?> createPost(Post post) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			String response = restTemplate.getForEntity(url, String.class).getBody();

			Post save = this.postRepository.save(post);

			log.info("Saved successfully :: " + save);

			PostResponse responseBody = new PostResponse();
			responseBody.setDb_post(save.toString());
			responseBody.setHttp_outbound(response);

			return ResponseEntity.ok(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");

		}
	}
}
