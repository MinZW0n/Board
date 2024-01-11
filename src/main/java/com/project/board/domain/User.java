package com.project.board.domain;

import com.project.board.domain.Comment;
import com.project.board.domain.BaseEntity;
import com.project.board.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> post;

    @OneToMany(mappedBy = "user")
    private List<Comment> comment;
}
