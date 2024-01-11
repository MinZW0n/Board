package com.project.board.controller;

import com.project.board.dto.BoardDto;
import com.project.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/boards")
    public String index(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "home";
    }

    @GetMapping("/boards/new")
    public String newBorad(){
        return "createBoardForm";
    }

    @PostMapping("/boards/new")
    public String createBoard(BoardDto boardDto){
        Long boardId = boardService.saveBoard(boardDto.toEntity());
        return "redirect:/boards";
    }

}
