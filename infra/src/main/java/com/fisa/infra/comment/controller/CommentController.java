package com.fisa.infra.comment.controller;

import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.dto.CommentDTO;
import com.fisa.infra.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/create")
    public String createForm(Model model) {
        model.addAttribute("commentSaveForm", new CommentDTO());
        return "entire/comment/commentSaveForm";
    }

    @PostMapping(value = "/create")
    public String writeComment(@ModelAttribute("commentDTO") CommentDTO commentDTO) {

        //사용자 로그인 정보 가지고 오기
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Account account = (Account) authentication.getPrincipal();
        Comment comment = commentService.writeComment(commentDTO);
        return "redirect:/comment/" + comment.getCommentId();
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