package com.example.qkare.orderSystem.Controller;

import com.example.qkare.orderSystem.Dto.CommentDto;
import com.example.qkare.orderSystem.Entity.Comment;
import com.example.qkare.orderSystem.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/createComment")
    public CommentDto createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Integer commentId){
        return commentService.deleteCommentById(commentId);
    }

    @GetMapping("getAllComments")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }
}
