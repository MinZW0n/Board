package com.project.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;

    @Builder
    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
