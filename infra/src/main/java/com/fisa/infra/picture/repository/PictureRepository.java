package com.fisa.infra.picture.repository;


import com.fisa.infra.picture.domain.Picture;
import org.springframework.data.repository.Repository;

public interface PictureRepository extends Repository<Picture,Long>, CommonPictureRepository {

}
