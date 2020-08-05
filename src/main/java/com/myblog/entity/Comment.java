package com.myblog.entity;

import java.math.BigInteger;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myblog.util.CommentParentType;
import com.myblog.util.PostgreSQLEnumType;

@Entity
@Table(name="comment")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class Comment {
	
    private static final long serialVersionUID = -2343243243242432341L;

	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid" , strategy = "org.hibernate.id.UUIDGenerator")
	private UUID commentId;
	private String comment;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false , targetEntity = BlogUser.class)
    @JoinColumn(name = "commentBy", nullable = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
	private BlogUser commentBy;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "parentType")
	@Type( type = "pgsql_enum" )
	private CommentParentType parentType;
	private UUID parentId;
	private BigInteger likes;
	private BigInteger disLikes;
	
	
	
	public Comment() {
		super();
	}


	public Comment(String comment, BlogUser commentBy, CommentParentType parentType, UUID parentId, BigInteger likes,
			BigInteger disLikes) {
		super();
		this.comment = comment;
		this.commentBy = commentBy;
		this.parentType = parentType;
		this.parentId = parentId;
		this.likes = likes;
		this.disLikes = disLikes;
	}


	public UUID getCommentId() {
		return commentId;
	}


	public void setCommentId(UUID commentId) {
		this.commentId = commentId;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public BlogUser getCommentBy() {
		return commentBy;
	}


	public void setCommentBy(BlogUser commentBy) {
		this.commentBy = commentBy;
	}


	public CommentParentType getParentType() {
		return parentType;
	}


	public void setParentType(CommentParentType parentType) {
		this.parentType = parentType;
	}


	public UUID getParentId() {
		return parentId;
	}


	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}


	public BigInteger getLikes() {
		return likes;
	}


	public void setLikes(BigInteger likes) {
		this.likes = likes;
	}


	public BigInteger getDisLikes() {
		return disLikes;
	}


	public void setDisLikes(BigInteger disLikes) {
		this.disLikes = disLikes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
