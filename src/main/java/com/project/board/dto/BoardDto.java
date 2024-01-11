package com.project.board.dto;

import com.project.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;


    public Board toEntity() {
        return Board.builder()
                .id(id)
                .title(title)
                .build();
    }

    @ConstructorProperties({"id", "title"})
    public BoardDto(Long id, String title){
        this.id = id;
        this.title = title;

    }
}
