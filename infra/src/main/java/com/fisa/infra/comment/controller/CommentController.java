package com.fisa.infra.comment.controller;

import com.fisa.infra.comment.dto.CommentDTO;
import com.fisa.infra.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     * @param commentDTO
     * http://localhost:8080/swagger-ui/index.html -> 스웨거
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> writeComment(@RequestBody CommentDTO commentDTO) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Account account = (Account) authentication.getPrincipal();

        try {
            commentService.writeComment(commentDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }

    /**
     * 댓글 조회
     * @param boardId
     * @return result
     * */
    @GetMapping(value="/read")
    public ResponseEntity<?> readComment(@RequestParam Long boardId) {
        try {
            List<CommentDTO> result = commentService.readComment(boardId);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }

}