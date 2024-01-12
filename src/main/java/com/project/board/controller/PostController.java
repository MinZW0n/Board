package com.project.board.controller;


import com.project.board.domain.Post;
import com.project.board.dto.PostDto;
import com.project.board.service.BoardService;
import com.project.board.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final BoardService boardService;


    @GetMapping("/{postId}")
    public String getPostDetail(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);

        return "post/post";
    }

    @GetMapping("/create")
    public String createPost(@RequestParam(name = "boardId") Long boardId, Model model) {

        model.addAttribute("boardId", boardId);
        return "post/createPost";
    }

    @PostMapping("/posts/create")
    public String createPostPost(@ModelAttribute("postDto") PostDto postDto, @RequestParam(name = "boardId") Long boardId) {
        System.out.println("Reached createPostPost method");
        Post post = postDto.toEntity();
        Post createdPost = postService.createPost(post, boardId);
        return "redirect:/boards/" + createdPost.getBoard().getId();
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "post/editPost";
    }

    // PostMapping 게시글 수정
//    @PostMapping("/{postId}/edit")
//    public String editPost(@PathVariable Long postId, @ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes) {
//        Post post = postDto.toEntity();
//        Post updatedPost = postService.updatePost(post, postId);
//
//        redirectAttributes.addAttribute("postId", updatedPost.getId());
//        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
//        return "redirect:/posts/{postId}";
//    }
//
//    // DeleteMapping 게시글 삭제
//    @DeleteMapping("/{postId}")
//    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
//        // postService를 사용하여 주어진 postId에 해당하는 게시물 삭제
//
//        // 삭제 성공 메시지를 FlashAttribute로 전달
//
//        // 게시물 목록 페이지로 리다이렉션
//
//    }
}
