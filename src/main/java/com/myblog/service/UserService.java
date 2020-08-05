package com.myblog.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.entity.BlogUser;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LogManager.getLogger(UserService.class);

	public List<BlogUser> getAllUsers() throws ResourceNotFoundException {
		List<BlogUser> users = Collections.emptyList();
		try {
			users = userRepository.findAll();
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(users).orElseThrow(() -> new ResourceNotFoundException("No users in the system"));
	}

	public Optional<BlogUser> getuserById(UUID userId) throws ResourceNotFoundException {

		Optional<BlogUser> user = Optional.empty();
		try {
			user = userRepository.findById(userId);
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(user)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:" + userId));
	}

	public Optional<BlogUser> save(BlogUser user) {

		try {
			return Optional.ofNullable(userRepository.save(user));
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<BlogUser> updateUserById(BlogUser user, UUID userId) {
		try {
			Optional<BlogUser> tempUser = getuserById(userId);
			user.setUserId(tempUser.get().getUserId());

			Optional<BlogUser> updateduser = Optional.of(userRepository.save(user));
			return updateduser;
		} catch (Exception e) {
			logger.error("unable to update user::" + e.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Void> deleteUser(UUID userId) {

		try {
			userRepository.deleteById(userId);
			return Optional.empty();
		} catch (Exception e) {
			logger.error("unable to delete User::" + e.getLocalizedMessage());

		}
		return Optional.of(null);
	}


}
