package com.example.qkare.orderSystem.Service;

import com.example.qkare.orderSystem.Dto.CommentDto;
import com.example.qkare.orderSystem.Entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDto convertEntityToDto(Comment comment);
    CommentDto createComment(CommentDto commentDto);
    String deleteCommentById(Integer commentId);
    List<Comment> getAllComments();
}
