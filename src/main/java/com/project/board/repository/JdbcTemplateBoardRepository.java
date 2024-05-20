package com.project.board.repository;

import com.project.board.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateBoardRepository {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTemplateBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Board> findAll() {
        return jdbcTemplate.query("SELECT * FROM board", RowMapper());
    }

    public Optional<Board> findById(Long id) {
        String sql = "select * from board where id = ?";
        return jdbcTemplate.query(sql, RowMapper(), id).stream().findAny();
    }

    public Board save(Board board) {
        String sql = "insert into board(name) value(?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, board.getName());
            return ps;
        }, keyHolder);

        long boardId = keyHolder.getKey().longValue();
        board.setId(boardId);
        return board;
    }

    public void update(Board board){
        String sql = "update board set name = ? where id = ?";
        jdbcTemplate.update(sql, board.getName(), board.getId());
    }


    public void delete(Long id){
        String sql = "delete from board where id = ?";
        jdbcTemplate.update(sql,id);
    }


    private RowMapper<Board> RowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setName(rs.getString("name"));
            return board;
        };
    }
}
