package com.project.board.service;

import com.project.board.domain.Board;
import com.project.board.global.exception.ExceptionCode;
import com.project.board.global.exception.ServiceLogicException;
import com.project.board.repository.JdbcTemplateBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final JdbcTemplateBoardRepository boardRepository;



    public BoardService(JdbcTemplateBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {

        return boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }

    public void updateBoard(Board board){
        final Board[] foundBoard = new Board[1];

        foundBoard[0] = boardRepository.findById(board.getId())
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        Optional.ofNullable(board.getName())
                .ifPresent(name -> { foundBoard[0] = foundBoard[0].toBuilder().name(name).build(); });


        boardRepository.update(foundBoard[0]);
    }

    public void deleteBoard(Long id){
        boardRepository.delete(id);
    }
}
