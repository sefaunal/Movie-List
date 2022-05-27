package com.sefaunal.movielist.Service;

import com.sefaunal.movielist.Model.Comment;
import com.sefaunal.movielist.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void createComment(Comment comment){
        commentRepository.save(comment);
    }

    public Comment findByID(long id){
        return commentRepository.findById(id).get();
    }
}
