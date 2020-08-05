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

import com.myblog.entity.BlogUser;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.service.UserService;
import com.myblog.util.ControllerResponse;

@RestController
@RequestMapping("api/v1")
public class UserController {
	
	
	 @Autowired
	    UserService userService;
	 
	 
	 @GetMapping("/users")
	    public ResponseEntity<List<BlogUser>> getAllUsers() throws ResourceNotFoundException {
	        List<BlogUser> users=userService.getAllUsers();
	        return ControllerResponse.getOkResposeEntity(users);

	    }
	 
	 @GetMapping("/users/{userId}")
	    public ResponseEntity<BlogUser> getUserById(@PathVariable(value = "userId") UUID userId) throws ResourceNotFoundException {
	        Optional<BlogUser> user = userService.getuserById(userId);
	        return user.map(ControllerResponse::getOkResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }

	    @PostMapping("/user")
	    public ResponseEntity<BlogUser> createUser(@RequestBody BlogUser user) {
	        Optional<BlogUser> newUser = userService.save(user);
	        return newUser.map(ControllerResponse::getCreatedResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);


	    }

	    @PutMapping("/users/{userId}")
	    public ResponseEntity<BlogUser> updateUser(@PathVariable(value ="userId") UUID userId, @RequestBody BlogUser user) throws ResourceNotFoundException {
	        Optional<BlogUser> updatedUser = userService.updateUserById(user, userId);
	        return updatedUser.map(ControllerResponse::getCreatedResposeEntity).orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }
	    
	    @DeleteMapping("/users/{userId}")
	    public void deleteUser(@PathVariable(value ="userId") UUID userId)  {
	    	userService.deleteUser(userId);

	    }

}
