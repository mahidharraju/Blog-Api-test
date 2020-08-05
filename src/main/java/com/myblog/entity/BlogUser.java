package com.myblog.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bloguser")
public class BlogUser {
	
	
    private static final long serialVersionUID = -2343243243242432341L;

    
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID userId;
	private String userName;
	
	@JsonIgnoreProperties(allowGetters = true)
	@JsonIgnore
	@OneToMany( mappedBy = "blogUser" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserRole> roles;

	
	
	
	public BlogUser() {
		super();
		roles= new HashSet<UserRole>();
	}



	public BlogUser(UUID userId) {
		super();
		this.userId = userId;
		roles= new HashSet<UserRole>();
	}

	public BlogUser(String userName) {
		super();
		this.userName = userName;
		roles= new HashSet<UserRole>();
		
	}



	public UUID getUserId() {
		return userId;
	}



	public void setUserId(UUID userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Set<UserRole> getRoles() {
		return roles;
	}



	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
		for(UserRole role : roles)
			role.setBlogUser(this);
	
	}
	
	
	
	
	

}
