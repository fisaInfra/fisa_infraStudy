package com.fisa.infra.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.infra.model.entity.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment,String>{

}
