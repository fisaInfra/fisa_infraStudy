package com.fisa.infra.reply.repository.jpa;

import com.fisa.infra.reply.domain.Reply;
import com.fisa.infra.reply.repository.basic.CommonReplyRepository;
import org.springframework.data.repository.Repository;

public interface ReplyRepository extends Repository<Reply,Long>, CommonReplyRepository {

}
