package com.myblog.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.myblog.entity.Author;
import com.myblog.entity.BlogUser;
import com.myblog.entity.Comment;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.UserRepository;
import com.myblog.util.CommentParentType;


@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LogManager.getLogger(CommentService.class);

	public List<Comment> getAllComments() throws ResourceNotFoundException {
		List<Comment> comments = Collections.emptyList();
		try {
			comments = commentRepository.findAll();
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(comments).orElseThrow(() -> new ResourceNotFoundException("No comments in the system"));
	}

	public Optional<Comment> getcommentById(UUID commentId) throws ResourceNotFoundException {

		Optional<Comment> comment = Optional.empty();
		try {
			comment = commentRepository.findById(commentId);
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(comment)
				.orElseThrow(() -> new ResourceNotFoundException("comment not found with id:" + commentId));
	}

	public Optional<Comment> save(Comment comment) {

		try {
			return Optional.ofNullable(commentRepository.save(comment));
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Comment> updateCommentById(Comment comment, UUID commentId) {
		try {
			Optional<Comment> tempComment = commentRepository.findById(commentId);
			comment.setCommentId(tempComment.get().getCommentId());

			Optional<Comment> updatedcomment = Optional.of(commentRepository.save(comment));
			return updatedcomment;
		} catch (Exception e) {
			logger.error("unable to update comment::" + e.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Void> deleteComment(UUID commentId) {

		try {
			commentRepository.deleteById(commentId);
			return Optional.empty();
		} catch (Exception e) {
			logger.error("unable to delete Comment::" + e.getLocalizedMessage());

		}
		return Optional.of(null);
	}

	public List<Comment> getCommentsByParentType(UUID parentId , CommentParentType type) throws ResourceNotFoundException {
		List<Comment> comments = Collections.emptyList();
		try {
			Comment comment = new Comment();
			comment.setParentType(type);
			comment.setParentId(parentId);
			
			 Example<Comment> commentExample = Example.of(comment, ExampleMatcher.matchingAll().withIgnoreCase());
			 comments = commentRepository.findAll(commentExample);
			    
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(comments).orElseThrow(() -> new ResourceNotFoundException("No comments in the system"));

	}



}
