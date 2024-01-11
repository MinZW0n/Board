package com.project.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board")
    private List<Post> post;

    @Builder
    public Board(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
