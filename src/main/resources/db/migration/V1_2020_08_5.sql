CREATE TYPE roleType AS ENUM (
    'ADMIN',
    'USER',
    'DEVELOPER',
    'AUTHOR'

);

CREATE TABLE BlogUser (
    user_id uuid NOT NULL,
    "user_name" character varying(500) NOT NULL
);

ALTER TABLE ONLY BlogUser
    ADD CONSTRAINT "BlogUser_pkey" PRIMARY KEY (user_id);



CREATE TABLE UserRole (
    role_id uuid NOT NULL,
    role roleType NOT NULL,
    "user_id" uuid NOT NULL,
    description character varying(1000) NOT NULL
);

ALTER TABLE ONLY UserRole
    ADD CONSTRAINT "UserRole_pkey" PRIMARY KEY (role_id);

ALTER TABLE ONLY UserRole
    ADD CONSTRAINT "FK_user_userId" FOREIGN KEY ("user_id") REFERENCES BlogUser(user_id) NOT VALID;

CREATE INDEX "fki_FK_user_userId" ON UserRole USING btree ("user_id");


CREATE TYPE parentType AS ENUM (
    'POST',
    'COMMENT'
);


CREATE TABLE Comment (
    comment_id uuid NOT NULL,
    comment text NOT NULL,
    "comment_by" uuid NOT NULL,
    "parent_type" parentType NOT NULL,
    "parent_id" uuid,
    likes integer,
    dis_likes integer
);

ALTER TABLE ONLY Comment
    ADD CONSTRAINT "Comment_pkey" PRIMARY KEY (comment_id);

ALTER TABLE ONLY Comment
    ADD CONSTRAINT "FK_CommentBy_UserId" FOREIGN KEY ("comment_by") REFERENCES BlogUser(user_id) NOT VALID;



    
    
    
    
