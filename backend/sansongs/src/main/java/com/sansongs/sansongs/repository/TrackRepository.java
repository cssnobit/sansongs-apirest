package com.sansongs.sansongs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sansongs.sansongs.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
	
	@EntityGraph(type = EntityGraphType.FETCH,
				attributePaths = {
						"artists",
						"artists.country",
						"artists.albuns",
						"album",
						"streamings",
						"typeMusic"
				})
	List<Track> findAll();
	
	@EntityGraph(type = EntityGraphType.FETCH,
			attributePaths = {
					"artists",
					"artists.country",
					"artists.albuns",
					"album",
					"streamings",
					"typeMusic"
			})
	Optional<Track> findById(Long id);
}
