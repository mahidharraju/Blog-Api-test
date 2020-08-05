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

import com.myblog.entity.UserRole;
import com.myblog.exceptions.ResourceNotFoundException;
import com.myblog.service.UserRoleService;
import com.myblog.util.ControllerResponse;
import com.myblog.util.RoleType;

@RestController
@RequestMapping("api/v1")
public class UserRoleController {
	
	
	 @Autowired
	    UserRoleService roleService;
	 
	 
	 @GetMapping("/roles")
	    public ResponseEntity<List<UserRole>> getAllRoles() throws ResourceNotFoundException {
	        List<UserRole> roles=roleService.getAllRoles();
	        return ControllerResponse.getOkResposeEntity(roles);

	    }
	 
	 @GetMapping("/{userId}/roles")
	    public ResponseEntity<List<RoleType>> getRolesByUser(@PathVariable(value = "userId") UUID userId) throws ResourceNotFoundException {
	        List<RoleType> roles=roleService.getRolesByUser(userId);
	        return ControllerResponse.getOkResposeEntity(roles);

	    }
	 
	 @GetMapping("/roles/{roleId}")
	    public ResponseEntity<UserRole> getRoleById(@PathVariable(value = "roleId") UUID roleId) throws ResourceNotFoundException {
	        Optional<UserRole> role = roleService.getroleById(roleId);
	        return role.map(ControllerResponse::getOkResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }

	    @PostMapping("/role")
	    public ResponseEntity<UserRole> createRole(@RequestBody UserRole role) {
	    	System.out.println(role);
	        Optional<UserRole> newRole = roleService.save(role);
	        return newRole.map(ControllerResponse::getCreatedResposeEntity).
	                orElseGet(ControllerResponse::getServerErrorResponseEntity);


	    }

	    @PutMapping("/roles/{roleId}")
	    public ResponseEntity<UserRole> updateRole(@PathVariable(value ="roleId") UUID roleId, @RequestBody UserRole role) throws ResourceNotFoundException {
	        Optional<UserRole> updatedRole = roleService.updateRoleById(role, roleId);
	        return updatedRole.map(ControllerResponse::getCreatedResposeEntity).orElseGet(ControllerResponse::getServerErrorResponseEntity);

	    }
	    
	    @DeleteMapping("/roles/{roleId}")
	    public void deleteRole(@PathVariable(value ="roleId") UUID roleId)  {
	    	roleService.deleteRole(roleId);

	    }

}
