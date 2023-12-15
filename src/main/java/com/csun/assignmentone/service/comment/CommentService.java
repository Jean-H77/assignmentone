package com.csun.assignmentone.service.comment;

import com.csun.assignmentone.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllByTaskId(Long id);

    Comment saveComment(Comment comment);

    void deleteCommentById(Long id);

    Comment updateComment(Comment comment, Long id);

}
