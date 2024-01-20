package com.sansongs.sansongs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sansongs.sansongs.model.Artist;
import com.sansongs.sansongs.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping
	public List<Artist> findAllArtists() {
		return artistService.getAllArtists();
	}
	
	@GetMapping("/{artistId}")
	public ResponseEntity<Artist> findArtistById(@PathVariable Long artistId) {
		Optional<Artist> artistFound = artistService.getArtistById(artistId);
		
		if(artistFound.isPresent()) {
			return ResponseEntity.ok(artistFound.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
