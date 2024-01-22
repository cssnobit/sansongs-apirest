package com.sansongs.sansongs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sansongs.sansongs.exception.ErrorInDataException;
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
	
	@PostMapping
	public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
		try {		
			Artist artistCreated = artistService.save(artist);
			return ResponseEntity.status(HttpStatus.CREATED).body(artistCreated);
		} catch(ErrorInDataException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{artistId}")
	public ResponseEntity<Artist> removeArtist(@PathVariable Long artistId) {
		Optional<Artist> artistFound = artistService.getArtistById(artistId);
		
		if(artistFound.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			artistService.remove(artistId);		
			
			return ResponseEntity.noContent().build();	
		} catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PutMapping("/{artistId}")
	public ResponseEntity<Artist> updateArtist(@PathVariable Long artistId,
			@RequestBody Artist artist) {
		
		Optional<Artist> artistFound = artistService.getArtistById(artistId);
		
		if(artistFound.isPresent()) {
			BeanUtils.copyProperties(artist, artistFound.get(), "id");
			try {				
				Artist artistUpdated = artistService.save(artistFound.get());
				
				return ResponseEntity.ok(artistUpdated);
			} catch(ErrorInDataException e) {
				return ResponseEntity.badRequest().build();
			}
		}
		
		return ResponseEntity.notFound().build();
	}
}
