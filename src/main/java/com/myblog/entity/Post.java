package com.myblog.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;


@Entity
@Table(name = "Post")
public class Post {

    private static final long serialVersionUID = -2343243243242432341L;
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false)
    private UUID postId;
    private  String title;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="author")
    private Author author;
    
    
    private String image;
    private LocalDateTime postedOn;
    private LocalDateTime lastUpdated;
    private BigInteger likesCount;
    private BigInteger disLikesCount;
    private String content;

    

    public Post() {
		super();
	}

	public Post(String title, String image, LocalDateTime postedOn, LocalDateTime lastUpdated, BigInteger likesCount, BigInteger disLikesCount, String content) {
        this.title = title;
        this.image = image;
        this.postedOn = postedOn;
        this.lastUpdated = lastUpdated;
        this.likesCount = likesCount;
        this.disLikesCount = disLikesCount;
        this.content = content;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID id) {
        this.postId = id;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        author.getPosts().add(this);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigInteger getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(BigInteger likesCount) {
        this.likesCount = likesCount;
    }

    public BigInteger getDisLikesCount() {
        return disLikesCount;
    }

    public void setDisLikesCount(BigInteger disLikesCount) {
        this.disLikesCount = disLikesCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
