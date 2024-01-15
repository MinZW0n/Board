package com.project.board.service;

import com.project.board.domain.Comment;
import com.project.board.domain.Post;
import com.project.board.dto.CommentDto;
import com.project.board.global.exception.ExceptionCode;
import com.project.board.global.exception.ServiceLogicException;
import com.project.board.repository.CommentRepository;
import com.project.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment findComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return comment;
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

    public Comment updateComment(Comment comment, Long commentId){
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> foundComment.setContent(content));

        return commentRepository.save(foundComment);
    }


    public void deleteComment(Long commentId){
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        commentRepository.delete(foundComment);
    }
}
