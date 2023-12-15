package com.csun.assignmentone.service.comment;

import com.csun.assignmentone.entity.Comment;
import com.csun.assignmentone.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllByTaskId(Long id) {
        return commentRepository.findAllByTaskId(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()) {
            Comment toUpdate = optionalComment.get();
            toUpdate.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            commentRepository.save(toUpdate);
        }
    }

    @Override
    public Comment updateComment(Comment comment, Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()) {
            Comment toUpdate = optionalComment.get();
            toUpdate.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            toUpdate.setTaskComment(comment.getTaskComment());
            return commentRepository.save(toUpdate);
        }
        return null;
    }
}
