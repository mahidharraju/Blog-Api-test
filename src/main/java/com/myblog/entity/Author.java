package com.myblog.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Table(name="Author")
public class Author {
	
	
	 private static final long serialVersionUID = -2343243243242432342L;	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(insertable = false, updatable = false)
	private UUID authorId;
	private String authorName;
	private String authorBio;
	private String image;
	
	@JsonIgnore
	@OneToMany(mappedBy = "author" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	private Set<Post> posts;
	
	
	
	public Author() {
		super();
		posts= new HashSet<Post>();
	}
	
	
	public Author(UUID authorId) {
		super();
		this.authorId = authorId;
		posts= new HashSet<Post>();
	}


	public Author(String authorName, String authorBio, String image) {
		super();
		this.authorName = authorName;
		this.authorBio = authorBio;
		this.image = image;
		posts= new HashSet<Post>();
	}
	
	
	
	
	public UUID getAuthorId() {
		return authorId;
	}
	public void setAuthorId(UUID id) {
		this.authorId = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorBio() {
		return authorBio;
	}
	public void setAuthorBio(String authorBio) {
		this.authorBio = authorBio;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
		 for (Post post : posts) {
	            post.setAuthor(this);
	        }
	}
	
	
	

}
