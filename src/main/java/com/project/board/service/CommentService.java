package com.project.board.service;

import com.project.board.domain.Comment;
import com.project.board.domain.Post;
import com.project.board.dto.CommentDto;
import com.project.board.global.exception.ExceptionCode;
import com.project.board.global.exception.ServiceLogicException;
import com.project.board.repository.CommentRepository;
import com.project.board.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> findComment(){
        return commentRepository.findAll();
    }

    public List<Comment> findCommentByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment(Comment comment, Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.POST_NOT_FOUND));

        comment.setPost(post);

        return commentRepository.save(comment);

    }
}
