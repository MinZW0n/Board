package com.project.board.dto;

import com.project.board.domain.Comment;
import com.project.board.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;

    public Comment toEntity(){
        return Comment.builder()
                .id(id)
                .content(content)
                .build();
    }

    @ConstructorProperties({"id", "content"})
    public CommentDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
