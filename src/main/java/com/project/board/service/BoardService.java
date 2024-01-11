package com.project.board.service;

import com.project.board.domain.Board;
import com.project.board.repository.JdbcTemplateBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final JdbcTemplateBoardRepository boardRepository;

    public BoardService(JdbcTemplateBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }
}
