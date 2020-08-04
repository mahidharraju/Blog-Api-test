--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3 (Ubuntu 12.3-1.pgdg18.04+1)
-- Dumped by pg_dump version 12.3 (Ubuntu 12.3-1.pgdg18.04+1)

-- Started on 2020-08-02 14:44:28 IST


--DROP DATABASE blogdb;
--
-- TOC entry 2984 (class 1262 OID 16423)
-- Name: blogdb; Type: DATABASE; Schema: -; Owner: postgres
--

--CREATE DATABASE blogdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_IN' LC_CTYPE = 'en_IN';


ALTER DATABASE blogdb OWNER TO postgres;

\connect blogdb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 633 (class 1247 OID 16456)
-- Name: parentType; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."parentType" AS ENUM (
    'POST',
    'COMMENT'
);


ALTER TYPE public."parentType" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16432)
-- Name: Author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Author" (
    id uuid NOT NULL,
    "authorName" character varying(500) NOT NULL,
    "authorBio" text NOT NULL,
    image character varying(2000) NOT NULL
);


ALTER TABLE public."Author" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16461)
-- Name: Comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Comment" (
    id uuid NOT NULL,
    comment text NOT NULL,
    "commentBy" uuid NOT NULL,
    "parentType" public."parentType" NOT NULL,
    "parentId" uuid,
    likes integer,
    dislikes integer
);


ALTER TABLE public."Comment" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16424)
-- Name: Post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Post" (
    id uuid NOT NULL,
    title character varying(500) NOT NULL,
    content text NOT NULL,
    "postedOn" timestamp without time zone NOT NULL,
    "lastUpdated" timestamp without time zone,
    "likesCount" integer,
    "disLikesCount" integer,
    image character varying(2000) NOT NULL,
    author uuid NOT NULL
);


ALTER TABLE public."Post" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16477)
-- Name: Role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Role" (
    id uuid NOT NULL,
    role character varying(500) NOT NULL,
    description character varying(1000) NOT NULL
);


ALTER TABLE public."Role" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16469)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    id uuid NOT NULL,
    "userName" character varying(500) NOT NULL,
    "userRole" uuid NOT NULL
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16516)
-- Name: UserRole; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."UserRole" (
    id uuid NOT NULL,
    "userID" uuid NOT NULL,
    "roleID" uuid NOT NULL
);


ALTER TABLE public."UserRole" OWNER TO postgres;

--
-- TOC entry 2974 (class 0 OID 16432)
-- Dependencies: 203
-- Data for Name: Author; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2975 (class 0 OID 16461)
-- Dependencies: 204
-- Data for Name: Comment; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2973 (class 0 OID 16424)
-- Dependencies: 202
-- Data for Name: Post; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2977 (class 0 OID 16477)
-- Dependencies: 206
-- Data for Name: Role; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2976 (class 0 OID 16469)
-- Dependencies: 205
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2978 (class 0 OID 16516)
-- Dependencies: 207
-- Data for Name: UserRole; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2833 (class 2606 OID 16468)
-- Name: Comment Comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Comment"
    ADD CONSTRAINT "Comment_pkey" PRIMARY KEY (id);


--
-- TOC entry 2840 (class 2606 OID 16520)
-- Name: UserRole PF_userRole_ID; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRole"
    ADD CONSTRAINT "PF_userRole_ID" PRIMARY KEY (id);


--
-- TOC entry 2828 (class 2606 OID 16431)
-- Name: Post PK_Id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Post"
    ADD CONSTRAINT "PK_Id" PRIMARY KEY (id);


--
-- TOC entry 2831 (class 2606 OID 16439)
-- Name: Author PK_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Author"
    ADD CONSTRAINT "PK_id" PRIMARY KEY (id);


--
-- TOC entry 2838 (class 2606 OID 16484)
-- Name: Role Role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Role"
    ADD CONSTRAINT "Role_pkey" PRIMARY KEY (id);


--
-- TOC entry 2835 (class 2606 OID 16476)
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


--
-- TOC entry 2829 (class 1259 OID 16504)
-- Name: fki_FK_author_authorId; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FK_author_authorId" ON public."Post" USING btree (author);


--
-- TOC entry 2841 (class 1259 OID 16531)
-- Name: fki_FK_userRoleID_roleID; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FK_userRoleID_roleID" ON public."UserRole" USING btree ("roleID");


--
-- TOC entry 2836 (class 1259 OID 16515)
-- Name: fki_FK_userRole_RoleId; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FK_userRole_RoleId" ON public."User" USING btree ("userRole");


--
-- TOC entry 2843 (class 2606 OID 16505)
-- Name: Comment FK_CommentBy_UserId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Comment"
    ADD CONSTRAINT "FK_CommentBy_UserId" FOREIGN KEY ("commentBy") REFERENCES public."User"(id) NOT VALID;


--
-- TOC entry 2842 (class 2606 OID 16499)
-- Name: Post FK_author_authorId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Post"
    ADD CONSTRAINT "FK_author_authorId" FOREIGN KEY (author) REFERENCES public."Author"(id) NOT VALID;


--
-- TOC entry 2845 (class 2606 OID 16521)
-- Name: UserRole FK_userID_user_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRole"
    ADD CONSTRAINT "FK_userID_user_ID" FOREIGN KEY ("userID") REFERENCES public."User"(id);


--
-- TOC entry 2846 (class 2606 OID 16526)
-- Name: UserRole FK_userRoleID_roleID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."UserRole"
    ADD CONSTRAINT "FK_userRoleID_roleID" FOREIGN KEY ("roleID") REFERENCES public."Role"(id) NOT VALID;


--
-- TOC entry 2844 (class 2606 OID 16510)
-- Name: User FK_userRole_RoleId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "FK_userRole_RoleId" FOREIGN KEY ("userRole") REFERENCES public."Role"(id) NOT VALID;


-- Completed on 2020-08-02 14:44:28 IST

--
-- PostgreSQL database dump complete
--

