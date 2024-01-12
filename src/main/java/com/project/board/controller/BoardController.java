package com.project.board.controller;

import com.project.board.dto.BoardDto;
import com.project.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "home";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "board";
    }

    @GetMapping("/boards/new")
    public String newBorad(){
        return "createBoardForm";
    }

    @PostMapping("/boards/new")
    public String createBoard(BoardDto boardDto){
        boardService.saveBoard(boardDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/boards/edit/{id}")
    public String editBoard(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("board", boardService.getBoardById(id));
        return "editBoardForm";
    }

    @PostMapping("/boards/edit/{id}")
    public String updateBoard(@PathVariable(name = "id") Long id, @ModelAttribute BoardDto boardDto){
        boardDto.setId(id);
        boardService.updateBoard(boardDto.toEntity());
        return "redirect:/";
    }

    @PostMapping("/boards/{id}")
    public String deleteBoard(@PathVariable(name = "id") Long id){
        boardService.deleteBoard(id);
        return "redirect:/";
    }
}
