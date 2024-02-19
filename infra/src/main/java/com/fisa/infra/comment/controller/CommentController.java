package com.fisa.infra.comment.controller;

import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.domain.dto.CommentDTO;
import com.fisa.infra.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

//    @GetMapping("/comment/create")
//    public String createForm(Model model) {
//        model.addAttribute("commentSaveForm", new CommentDTO());
//        return "entire/comment/commentSaveForm";
//    }
//
//    @PostMapping(value = "/comment/create")
//    public String writeComment(@ModelAttribute("commentDTO") CommentDTO commentDTO) {
//
//        //사용자 로그인 정보 가지고 오기
//        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //Account account = (Account) authentication.getPrincipal();
//        Comment comment = commentService.writeComment(commentDTO);
//        return "redirect:/comment/" + comment.getCommentId();
//    }


        //사용자 로그인 정보 가지고 오기
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Account account = (Account) authentication.getPrincipal();
//         Comment comment = commentService.writeComment(commentDTO);

//         return "redirect:/comment/" + comment.getCommentId();
//       }



//    @GetMapping("/comment/read")
//    public String readForm(Model model) {
//        Long boardId = 1L;
//        List<CommentDTO> result = commentService.readComment(boardId);
//
//        log.info("comment controller = {}",result.get(0).toString());
//        model.addAttribute("commentList", result);
//        return "entire/comment/commentReadForm";
//    }



//    @PostMapping(value = "/comment/read")
//    public String readComment(@RequestParam("boardId") Long boardId, Model model) {
//
//        //사용자 로그인 정보 가지고 오기
//        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //Account account = (Account) authentication.getPrincipal();
//        List<CommentDTO> result = commentService.readComment(boardId);
//
//        model.addAttribute("comments", result);
//        return "redirect:/api/comment/read?boardId=1";
//
////        return "entire/comment/commentReadForm"; // 또는 다른 뷰로 리턴
//
////        return "redirect:/";
//
//
////        return result;
//    }

    @GetMapping(value="comment/delete")
    public ResponseEntity<?> deleteComment(@RequestParam Long commentId) {
        try {
            commentService.deleteComment(commentId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }

    /**
     * 댓글 작성
     * @param commentDTO
     * @return ResponseEntity
     * http://localhost:8080/swagger-ui/index.html -> 스웨거
     */
    @PostMapping(value = "comment/create")
    public ResponseEntity<?> writeComment(@RequestBody CommentDTO commentDTO) {
        //Account account = (Account) authentication.getPrincipal();

        try {
//            Comment comment = commentService.writeComment(commentDTO);
            commentService.writeComment(commentDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError() // Error 500
                    .body(e.getMessage());
        }
    }

    /**
     * 댓글 조회
     * @param boardId
     * @return result
     * */
    @GetMapping(value="comment/read")
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