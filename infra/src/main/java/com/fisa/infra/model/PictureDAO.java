package com.fisa.infra.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.infra.model.entity.Picture;

@Repository
public interface PictureDAO extends JpaRepository<Picture,String> {

}
