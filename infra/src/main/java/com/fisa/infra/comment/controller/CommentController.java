package com.fisa.infra.comment.controller;

import com.fisa.infra.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @PostMapping(value = "/create")
//    public ResponseEntity<?> writeComment(@RequestParam CommentDTO commentDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Account account = (Account) authentication.getPrincipal();
//        try {
//            Comment comment = commentService.writeComment(null, null, null);
//
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity
//                    .internalServerError() // Error 500
//                    .body(e.getMessage());
//        }
//    }

}