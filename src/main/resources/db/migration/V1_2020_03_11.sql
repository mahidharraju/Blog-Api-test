CREATE TABLE Post (
    post_id uuid NOT NULL,
    title character varying(500) NOT NULL,
    content text NOT NULL,
    "posted_on" timestamp without time zone NOT NULL,
    "last_updated" timestamp without time zone,
    "likes_count" integer,
    "dis_likes_count" integer,
    image character varying(2000) NOT NULL,
    author uuid NOT NULL
);

ALTER TABLE ONLY Post
    ADD CONSTRAINT "PK_Id" PRIMARY KEY (post_id);

CREATE TABLE Author (
    author_id uuid NOT NULL,
    "author_name" character varying(500) NOT NULL,
    "author_bio" text NOT NULL,
    image character varying(2000) NOT NULL
);

ALTER TABLE ONLY Author
    ADD CONSTRAINT "PK_id" PRIMARY KEY (author_id);
    
ALTER TABLE ONLY Post
    ADD CONSTRAINT "FK_author_authorId" FOREIGN KEY (author) REFERENCES Author(author_id) NOT VALID;


    
    
    
    
