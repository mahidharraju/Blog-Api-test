package com.myblog.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.entity.Comment;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.service.CommentService;
import com.myblog.util.CommentParentType;
import com.myblog.util.ControllerResponse;

@RestController
@RequestMapping("api/v1")
public class CommentController {
	
	
	 @Autowired
	    CommentService commentService;
	 
	 
	 @GetMapping("/comments")
	    public ResponseEntity<List<Comment>> getAllComments() throws ResourceNotFoundException {
	        List<Comment> comments=commentService.getAllComments();
	        return ControllerResponse.getOkResposeEntity(comments);

	    }
	 
	 @GetMapping("/comments/{commentId}")
	    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "commentId") UUID commentId) throws ResourceNotFoundException {
	        Optional<Comment> comment = commentService.getcommentById(commentId);
	        return comment.map(ControllerResponse::getOkResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }
	 @GetMapping("/posts/{postId}/comments")
	    public ResponseEntity<List<Comment>> getCommentByPostId(@PathVariable(value = "postId") UUID postId) throws ResourceNotFoundException {
	        List<Comment> comments = commentService.getCommentsByParentType(postId, CommentParentType.POST);
	        return ControllerResponse.getOkResposeEntity(comments);

	    }
	 
	 	
	 	@GetMapping("/post/commnet/{commentId}")
	 	public ResponseEntity<List<Comment>> getCommentsOfAComment(@PathVariable(value = "commentId") UUID commentId) throws ResourceNotFoundException {
	        List<Comment> comments = commentService.getCommentsByParentType(commentId , CommentParentType.COMMENT);
	        return ControllerResponse.getOkResposeEntity(comments);

	    }

	    @PostMapping("/comment")
	    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
	        Optional<Comment> newComment = commentService.save(comment);
	        return newComment.map(ControllerResponse::getCreatedResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);


	    }

	    @PutMapping("/comments/{commentId}")
	    public ResponseEntity<Comment> updateComment(@PathVariable(value ="commentId") UUID commentId, @RequestBody Comment comment) throws ResourceNotFoundException {
	        Optional<Comment> updatedComment = commentService.updateCommentById(comment, commentId);
	        return updatedComment.map(ControllerResponse::getCreatedResposeEntity).orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }
	    
	    @DeleteMapping("/comments/{commentId}")
	    public void deleteComment(@PathVariable(value ="commentId") UUID commentId)  {
	    	commentService.deleteComment(commentId);

	    }

}
