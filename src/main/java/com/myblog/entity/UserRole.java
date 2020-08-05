package com.myblog.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.myblog.util.PostgreSQLEnumType;
import com.myblog.util.RoleType;

@Entity
@Table(name="userrole")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class UserRole {
	
	
	 private static final long serialVersionUID = -2343243243242432342L;	

	 
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid" , strategy = "org.hibernate.id.UUIDGenerator")
	private UUID roleId;
	
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "roleType")
	@Type( type = "pgsql_enum" )
	private RoleType role;
	
	
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private BlogUser blogUser;
	
	
	

	public UserRole() {
		super();
		
	}



	public UserRole(RoleType role, String description, BlogUser blogUser) {
		super();
		this.role = role;
		this.description = description;
		this.blogUser = blogUser;
	}

	public UUID getRoleId() {
		return roleId;
	}



	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}



	public RoleType getRole() {
		return role;
	}



	public void setRole(RoleType role) {
		this.role = role;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public BlogUser getBlogUser() {
		return blogUser;
	}



	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}




	
}
