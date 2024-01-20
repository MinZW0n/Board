package com.project.board.controller;

import com.project.board.domain.Board;
import com.project.board.domain.Post;
import com.project.board.dto.BoardDto;
import com.project.board.dto.BoardImageUploadDto;
import com.project.board.service.BoardService;
import com.project.board.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    private final BoardService boardService;

    private final PostService postService;

    public BoardController(BoardService boardService, PostService postService) {
        this.boardService = boardService;
        this.postService = postService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "board/boards";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable(name = "id") Long id,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "10") int size,
                           @RequestParam(name = "keyword", required = false) String keyword,
                           Model model) {
        Board board = boardService.getBoardById(id);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = postService.findPostsByBoardAndKeyword(board, keyword, pageRequest);

        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);

        return "board/board";
    }

    @GetMapping("/boards/create")
    public String newBorad(){
        return "board/createBoard";
    }

    @PostMapping("/boards/create")
    public String createBoard(BoardDto boardDto, @ModelAttribute BoardImageUploadDto boardImageUploadDto){
        boardService.saveBoard(boardDto, boardImageUploadDto);
        return "redirect:/";
    }

    @GetMapping("/boards/{id}/edit")
    public String editBoard(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("board", boardService.getBoardById(id));
        return "board/editBoard";
    }

    @PostMapping("/boards/{id}/edit")
    public String updateBoard(@PathVariable(name = "id") Long id, @ModelAttribute BoardDto boardDto){
        boardDto.setId(id);
        boardService.updateBoard(boardDto.toEntity());
        return "redirect:/";
    }

    @DeleteMapping("/boards/{id}/delete")
    public String deleteBoard(@PathVariable(name = "id") Long id){
        boardService.deleteBoard(id);
        return "redirect:/";
    }
}
