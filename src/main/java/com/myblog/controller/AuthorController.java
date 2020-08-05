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

import com.myblog.entity.Author;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.service.AuthorService;
import com.myblog.util.ControllerResponse;

@RestController
@RequestMapping("api/v1")
public class AuthorController {
	
	
	 @Autowired
	    AuthorService authorService;
	 
	 
	 @GetMapping("/authors")
	    public ResponseEntity<List<Author>> getAllAuthors() throws ResourceNotFoundException {
	        List<Author> authors=authorService.getAllAuthors();
	        return ControllerResponse.getOkResposeEntity(authors);

	    }
	 
	 @GetMapping("/authors/{authorId}")
	    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "authorId") UUID authorId) throws ResourceNotFoundException {
	        Optional<Author> author = authorService.getAuthorById(authorId);
	        return author.map(ControllerResponse::getOkResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }

	    @PostMapping("/author")
	    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
	        Optional<Author> newAuthor = authorService.save(author);
	        return newAuthor.map(ControllerResponse::getCreatedResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);


	    }

	    @PutMapping("/authors/{authorId}")
	    public ResponseEntity<Author> updateAuthor(@PathVariable(value ="authorId") UUID authorId, @RequestBody Author author) throws ResourceNotFoundException {
	        Optional<Author> updatedAuthor = authorService.updateAuthorById(author, authorId);
	        return updatedAuthor.map(ControllerResponse::getCreatedResposeEntity).orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }
	    
	    @DeleteMapping("/authors/{authorId}")
	    public void deleteAuthor(@PathVariable(value ="authorId") UUID authorId)  {
	    	authorService.deleteAuthor(authorId);

	    }

}
