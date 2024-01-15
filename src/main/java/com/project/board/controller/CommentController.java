package com.project.board.controller;

import com.project.board.domain.Comment;
import com.project.board.dto.CommentDto;
import com.project.board.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String createComment(@ModelAttribute CommentDto commentDto, @RequestParam(name = "postId") Long postId, RedirectAttributes redirectAttributes){
        Comment comment = commentDto.toEntity();
        commentService.createComment(comment,postId);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/{commentId}/edit")
    public String updateComment(@ModelAttribute CommentDto commentDto, @PathVariable(name = "commentId") Long commentId, RedirectAttributes redirectAttributes){
        Comment comment = commentDto.toEntity();
        Comment updatedComment = commentService.updateComment(comment, commentId);
        redirectAttributes.addAttribute("postId", updatedComment.getPost().getId());
        return "redirect:/posts/{postId}";
    }

}
