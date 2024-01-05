package com.sansongs.sansongs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sansongs.sansongs.model.Track;
import com.sansongs.sansongs.repository.TrackRepository;

@RestController
@RequestMapping("/tracks")
public class TrackController {
	
	@Autowired
	private TrackRepository trackRepository;
	
	@GetMapping
	public List<Track> findAll(){
		return trackRepository.findAll();
	}
	
	@GetMapping("/{trackId}")
	public ResponseEntity<Track> findById(@PathVariable Long trackId) {
		Optional<Track> trackFound = trackRepository.findById(trackId);
		
		if(trackFound.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(trackFound.get());
	}
}
