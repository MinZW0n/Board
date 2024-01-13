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
    public String getPostDetail(@PathVariable(name = "postId") Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);

        return "post/post";
    }

    @GetMapping("/create")
    public String createPost(@RequestParam(name = "boardId") Long boardId, Model model) {

        model.addAttribute("boardId", boardId);
        return "post/createPost";
    }

    @PostMapping("/create")
    public String createPostPost(@ModelAttribute("postDto") PostDto postDto, @RequestParam(name = "boardId") Long boardId) {
        System.out.println("Reached createPostPost method");
        Post post = postDto.toEntity();
        Post createdPost = postService.createPost(post, boardId);
        return "redirect:/boards/" + createdPost.getBoard().getId();
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable(name = "postId") Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "post/editPost";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable(name = "postId") Long postId, @ModelAttribute("postDto") PostDto postDto, RedirectAttributes redirectAttributes) {
        Post post = postDto.toEntity();
        Post updatedPost = postService.updatePost(post, postId);

        redirectAttributes.addAttribute("postId", updatedPost.getId());
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable(name = "postId") Long postId, RedirectAttributes redirectAttributes) {

        postService.deletePost(postId);

        redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");

        Post post = postService.findPost(postId);
        return  "redirect:/boards/" + post.getBoard().getId();
    }
}
