package com.sansongs.sansongs.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sansongs.sansongs.model.Track;
import com.sansongs.sansongs.repository.TrackRepository;
import com.sansongs.sansongs.service.TrackService;

@RestController
@RequestMapping("/tracks")
public class TrackController {
	
	@Autowired
	private TrackRepository trackRepository;
	
	@Autowired
	private TrackService trackService;
	
	@GetMapping
	public List<Track> findAllTracks(){
		return trackService.getAllTracks();
	}
	
	@GetMapping("/{trackId}")
	public ResponseEntity<Track> findTrackById(@PathVariable Long trackId) {
		Optional<Track> trackFound = trackService.getTrackById(trackId);
		
		if(trackFound.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(trackFound.get());
	}
	
//	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<?> addTrack(@RequestBody Track track) {
		try {
			return ResponseEntity.created(null).body(trackService.save(track));			
		} catch(PropertyValueException e) {			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
