package com.myblog.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.entity.Author;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.repository.AuthorRepository;

@Service
public class AuthorService {

	
	@Autowired
	AuthorRepository authorRepository;

	private static final Logger logger = LogManager.getLogger(AuthorService.class);

	public List<Author> getAllAuthors() throws ResourceNotFoundException {
		List<Author> authors = Collections.emptyList();
		try {
			authors = authorRepository.findAll();
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(authors).orElseThrow(() -> new ResourceNotFoundException("No authors int the system"));
	}

	public Optional<Author> getAuthorById(UUID authorId) throws ResourceNotFoundException {

		Optional<Author> author = Optional.empty();
		try {
			author = authorRepository.findById(authorId);
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(author)
				.orElseThrow(() -> new ResourceNotFoundException("author not found with id:" + authorId));
	}

	public Optional<Author> save(Author author) {

		try {
			return Optional.ofNullable(authorRepository.save(author));
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Author> updateAuthorById(Author author, UUID authorId) {
		try {
			Optional<Author> tempAuthor = getAuthorById(authorId);
			author.setAuthorId(tempAuthor.get().getAuthorId());

			Optional<Author> updaetedAuthor = Optional.of(authorRepository.save(author));
			return updaetedAuthor;
		} catch (Exception e) {
			logger.error("unable to update author::" + e.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Void> deleteAuthor(UUID authorId) {

		try {
			authorRepository.deleteById(authorId);
			return Optional.empty();
		} catch (Exception e) {
			logger.error("unable to delete Author::" + e.getLocalizedMessage());

		}
		return Optional.of(null);
	}

}
