package com.project.board.service;

import com.project.board.domain.Board;
import com.project.board.domain.BoardImage;
import com.project.board.dto.BoardDto;
import com.project.board.dto.BoardImageUploadDto;
import com.project.board.global.exception.ExceptionCode;
import com.project.board.global.exception.ServiceLogicException;
import com.project.board.repository.BoardImageRepository;
import com.project.board.repository.BoardRepository;
import com.project.board.repository.JdbcTemplateBoardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    private final JdbcTemplateBoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final BoardRepository boardRepository2;


    @Value("${file.boardImagePath}")
    private String uploadFolder;



    public BoardService(JdbcTemplateBoardRepository boardRepository, BoardImageRepository boardImageRepository, BoardRepository boardRepository2) {
        this.boardRepository = boardRepository;
        this.boardImageRepository = boardImageRepository;
        this.boardRepository2 = boardRepository2;
    }

    public List<Board> getAllBoards(){
        return boardRepository2.findAll();
    }

    public Board getBoardById(Long id) {

        return boardRepository.findById(id)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public Board saveBoard(BoardDto boardDto, BoardImageUploadDto boardImageUploadDto){

        Board boardEntity = boardDto.toEntity();
        Board savedBoard = boardRepository.save(boardEntity);

        if (boardImageUploadDto.getFiles() != null && !boardImageUploadDto.getFiles().isEmpty()) {
            for (MultipartFile file : boardImageUploadDto.getFiles()) {
                UUID uuid = UUID.randomUUID();
                String imageFileName = uuid + "_" + file.getOriginalFilename();

                File destinationFile = new File(uploadFolder + imageFileName);

                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String imageUrl = imageFileName;
                boardDto.addImageUrl(imageUrl);

                BoardImage image = BoardImage.builder()
                        .url(imageUrl)
                        .board(savedBoard)
                        .build();

                boardImageRepository.save(image);
                boardEntity.setBoardImage(image);
            }
        }
        // 모든 이미지 처리 후 Board 객체 저장
        Board result = boardRepository2.save(boardEntity);
        return result;
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
        boardRepository2.deleteById(id);
    }
}
