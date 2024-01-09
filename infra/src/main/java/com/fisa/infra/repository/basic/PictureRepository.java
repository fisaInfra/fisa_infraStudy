package com.fisa.infra.repository.basic;


import com.fisa.infra.domain.entity.Picture;
import com.fisa.infra.repository.common.CommonPictureRepository;
import org.springframework.data.repository.Repository;

public interface PictureRepository extends Repository<Picture,Long>, CommonPictureRepository {

}
