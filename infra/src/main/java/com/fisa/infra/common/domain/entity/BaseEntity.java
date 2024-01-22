package com.fisa.infra.common.domain.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.sound.sampled.AudioFileFormat;
import java.time.LocalDateTime;


/*
* 해당 추상 클래스를 사용해서 중복되는 컬럼들을 하나의 코드로 묶어 사용합니다.
* 해당 클래스는 엔티티는 아닌 그냥 추상 클래스임을 주의하세요
* */
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@EntityListeners(AudioFileFormat.class)
public abstract class BaseEntity {


    @CreatedDate
    @Column(updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP NOT NULL COMMENT '생성일자'")
    private LocalDateTime createdTime;


    @LastModifiedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정일자'")
    private LocalDateTime modifiedTime;


    @Setter
    @Column(columnDefinition = "bit default false NOT NULL COMMENT '이용가능여부'")
    public Boolean isDeleted;


}
