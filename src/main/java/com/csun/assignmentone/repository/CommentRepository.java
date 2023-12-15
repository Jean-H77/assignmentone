package com.csun.assignmentone.repository;

import com.csun.assignmentone.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository
        extends CrudRepository<Comment, Long> {

    List<Comment> findAllByTaskId(Long taskId);
}
