package com.project.board.controller;

import com.project.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}
