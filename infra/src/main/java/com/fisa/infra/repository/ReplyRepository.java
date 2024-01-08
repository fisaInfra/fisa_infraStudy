package com.fisa.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisa.infra.domain.entity.Reply;
import org.springframework.data.repository.Repository;

public interface ReplyRepository extends Repository<Reply,Long> {

}
