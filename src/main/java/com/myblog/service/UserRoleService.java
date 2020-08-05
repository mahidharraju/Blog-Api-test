package com.myblog.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.myblog.entity.BlogUser;
import com.myblog.entity.Comment;
import com.myblog.entity.UserRole;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.repository.UserRoleRepository;
import com.myblog.util.RoleType;


@Service
public class UserRoleService {
	
	@Autowired
	UserRoleRepository userRoleRepository;

	private static final Logger logger = LogManager.getLogger(UserRoleService.class);

	public List<UserRole> getAllRoles() throws ResourceNotFoundException {
		List<UserRole> roles = Collections.emptyList();
		try {
			roles = userRoleRepository.findAll();
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(roles).orElseThrow(() -> new ResourceNotFoundException("No roles in the system"));
	}

	public Optional<UserRole> getroleById(UUID roleId) throws ResourceNotFoundException {

		Optional<UserRole> role = Optional.empty();
		try {
			role = userRoleRepository.findById(roleId);
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(role)
				.orElseThrow(() -> new ResourceNotFoundException("role not found with id:" + roleId));
	}

	public Optional<UserRole> save(UserRole role) {
System.out.println(role.getBlogUser());
System.out.println(role.getBlogUser().getUserId());

		try {
			return Optional.ofNullable(userRoleRepository.save(role));
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<UserRole> updateRoleById(UserRole role, UUID roleId) {
		try {
			Optional<UserRole> tempRole = getroleById(roleId);
			role.setRoleId(tempRole.get().getRoleId());

			Optional<UserRole> updatedrole = Optional.of(userRoleRepository.save(role));
			return updatedrole;
		} catch (Exception e) {
			logger.error("unable to update role::" + e.getLocalizedMessage());

		}
		return Optional.empty();
	}

	public Optional<Void> deleteRole(UUID roleId) {

		try {
			userRoleRepository.deleteById(roleId);
			return Optional.empty();
		} catch (Exception e) {
			logger.error("unable to delete Role::" + e.getLocalizedMessage());

		}
		return Optional.of(null);
	}

	public List<RoleType> getRolesByUser(UUID userId) throws ResourceNotFoundException {
		
		List<UserRole> userRoles = Collections.emptyList();
		try {
		
		UserRole userRole = new UserRole();
		BlogUser user = new BlogUser();
		user.setUserId(userId);
		userRole.setBlogUser(user);
		 Example<UserRole> userRoleExample = Example.of(userRole, ExampleMatcher.matchingAll().withIgnoreCase());
		 userRoles = userRoleRepository.findAll(userRoleExample);
		} catch (Exception ex) {
			logger.error(ex.getLocalizedMessage());
		}
		return Optional.ofNullable(userRoles.stream().map(userRole -> userRole.getRole()).collect(Collectors.toList())).orElseThrow(() -> new ResourceNotFoundException("No comments in the system"));
	}


}
