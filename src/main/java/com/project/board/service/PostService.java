package com.project.board.service;

import com.project.board.domain.Board;
import com.project.board.domain.Post;
import com.project.board.global.exception.ExceptionCode;
import com.project.board.global.exception.ServiceLogicException;
import com.project.board.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    private final BoardService boardService;

    public PostService(PostRepository postRepository, BoardService boardService) {
        this.postRepository = postRepository;
        this.boardService = boardService;
    }

    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest){
        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findAllByBoardAndTitleContaining(board, keyword, pageRequest);
        } else {
            return postRepository.findAllByBoardOrderByCreatedAtDesc(board, pageRequest);
        }
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));
    }

    public Post createPost(Post post, Long boardId) {
        Board boardToCreate = boardService.getBoardById(boardId);
        post.setBoard(boardToCreate);
        Post savedPost = postRepository.save(post);

        return savedPost;
    }

}
