package com.sansongs.sansongs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sansongs.sansongs.model.Album;
import com.sansongs.sansongs.model.Artist;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	
	@EntityGraph(type = EntityGraphType.FETCH,
			attributePaths = {
					"artists",
					"artists.country",
					"artists.albuns",
					"tracks",
					"tracks.artists",
					"tracks.streamings",
					"tracks.typeMusic"
			})
	List<Album> findAll();
	
	@EntityGraph(type = EntityGraphType.FETCH,
			attributePaths = {
					"artists",
					"artists.country",
					"artists.albuns",
					"tracks",
					"tracks.artists",
					"tracks.streamings",
					"tracks.typeMusic"
			})
	Optional<Album> findById(Long id);
}
