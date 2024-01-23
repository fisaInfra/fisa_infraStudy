package com.fisa.infra.comment.controller;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.dto.CommentDTO;
import com.fisa.infra.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     * @param commentDTO
     * @return ResponseEntity
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> writeComment(@RequestBody CommentDTO commentDTO) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Account account = (Account) authentication.getPrincipal();
        ////account.getLoginId();

        try {
            Comment comment = commentService.writeComment(commentDTO);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }


}