package com.myblog.service;

import com.myblog.entity.Author;
import com.myblog.entity.Post;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.repository.AuthorRepository;
import com.myblog.repository.PostRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	AuthorRepository authorRepository;

	private static final Logger logger = LogManager.getLogger(PostService.class);

	public List<Post> getAllPosts() throws ResourceNotFoundException {
		List<Post> posts = Collections.emptyList();
		try {
			posts = postRepository.findAll();
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(posts).orElseThrow(() -> new ResourceNotFoundException("No posts int the system"));
	}

	public Optional<Post> getPostById(UUID postId) throws ResourceNotFoundException {

		Optional<Post> post = Optional.empty();
		try {
			post = postRepository.findById(postId);
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(post)
				.orElseThrow(() -> new ResourceNotFoundException("post not found with id:" + postId));
	}

	public Optional<Post> save(Post post) {

		try {
			post.setPostedOn(LocalDateTime.now());
			return Optional.ofNullable(postRepository.save(post));
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Post> updatePostbyId(Post post, UUID postId) {
		try {
			Optional<Post> tempPost = getPostById(postId);
			post.setPostId(tempPost.get().getPostId());
			post.setLastUpdated(LocalDateTime.now());

			Optional<Post> updatedPost = Optional.of(postRepository.save(post));
			return updatedPost;
		} catch (Exception e) {
			logger.error("unable to update Post::" + e.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Void> deletePost(UUID postId) {

		try {
			postRepository.deleteById(postId);
			return Optional.empty();
		} catch (Exception e) {
			logger.error("unable to delete post::" + e.getLocalizedMessage());

		}
		return Optional.of(null);
	}

}
