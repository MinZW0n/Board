package com.project.board.repository;

import com.project.board.domain.Board;
import com.project.board.domain.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTemplateBoardRepository {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTemplateBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Board> findAll() {
        return jdbcTemplate.query("SELECT * FROM board", postRowMapper());
    }



    private RowMapper<Board> postRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setTitle(rs.getString("title"));
            return board;
        };
    }
}
