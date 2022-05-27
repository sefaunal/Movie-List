package com.sefaunal.movielist.Repository;

import com.sefaunal.movielist.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = " SELECT * FROM movie m "
            + "left join movie_actors ma on ma.movie_movieid = m.movieid "
            + "left join actor a on a.actorid = ma.actor_list_actorid "
            + "left join movie_genres mg on mg.movie_movieid = m.movieid "
            + "left join genre g on g.genreid = mg.genre_list_genreid "
            + "where m.movie_name Like concat('%', :searchParam, '%') "
            + "or g.genre Like concat('%', :searchParam, '%') "
            + "or a.actor Like concat('%', :searchParam, '%') "
            + "group by m.movieid ORDER BY movie_release_date DESC",
    nativeQuery = true)
    List<Movie>Search(String searchParam);
}
