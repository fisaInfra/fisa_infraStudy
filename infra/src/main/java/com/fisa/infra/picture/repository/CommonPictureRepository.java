package com.fisa.infra.picture.repository;


import com.fisa.infra.picture.domain.Picture;

/*
* 해당 인터페이스를 통해서 메서드를 명시할 때 사용합니다.
* */
public interface CommonPictureRepository {

    Picture findById(Long id);

}
