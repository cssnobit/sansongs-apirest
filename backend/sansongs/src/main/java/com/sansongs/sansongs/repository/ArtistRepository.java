package com.sansongs.sansongs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sansongs.sansongs.model.Artist;
import com.sansongs.sansongs.model.Track;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
	@EntityGraph(type = EntityGraphType.FETCH,
			attributePaths = {
					"country",
					"albuns"
			})
	List<Artist> findAll();
	
	@EntityGraph(type = EntityGraphType.FETCH,
			attributePaths = {
					"country",
					"albuns"
			})
	Optional<Artist> findById(Long id);
}
