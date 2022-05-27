package com.sefaunal.movielist.Repository;

import com.sefaunal.movielist.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE movieid = :movieID ", nativeQuery = true)
    List<Comment>findByMovieID(Long movieID);

    @Query(value = "SELECT * FROM comment WHERE userid = :userID ", nativeQuery = true)
    List<Comment>findByUserID(int userID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comment WHERE movieid = :movieID ", nativeQuery = true)
    void deleteByMovieID(Long movieID);
}
