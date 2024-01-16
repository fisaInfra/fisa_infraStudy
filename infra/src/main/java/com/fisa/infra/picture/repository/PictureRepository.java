package com.fisa.infra.picture.repository;


import com.fisa.infra.common.entity.Picture;
import org.springframework.data.repository.Repository;

public interface PictureRepository extends Repository<Picture,Long>, CommonPictureRepository {

}
