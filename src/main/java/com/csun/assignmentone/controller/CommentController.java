package com.csun.assignmentone.controller;

import com.csun.assignmentone.entity.Comment;
import com.csun.assignmentone.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/tasks/comments/{id}")
    public List<Comment> getComments(@PathVariable Long id) {
        return commentService.getAllByTaskId(id);
    }

    @PostMapping("tasks/comments/{id}")
    public Comment saveComment(@RequestBody Comment comment, @PathVariable Long id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setCreatedAt(timestamp);
        comment.setId(id);
        return commentService.saveComment(comment);
    }

    @DeleteMapping("tasks/comments/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
    }
}
