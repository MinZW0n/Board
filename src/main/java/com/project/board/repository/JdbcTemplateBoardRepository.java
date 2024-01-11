package com.project.board.repository;

import com.project.board.domain.Board;
import com.project.board.domain.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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

    public Board save(Board board) {
        String sql = "insert into board(title) value(?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, board.getTitle());
            return ps;
        }, keyHolder);

        long postId = keyHolder.getKey().longValue();
        board.setId(postId);
        return board;
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
