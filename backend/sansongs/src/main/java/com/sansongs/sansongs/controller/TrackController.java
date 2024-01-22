package com.sansongs.sansongs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.sansongs.sansongs.model.Track;
import com.sansongs.sansongs.service.TrackService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/tracks")
public class TrackController {

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

	@PostMapping
	public ResponseEntity<Track> addTrack(@RequestBody Track track) {
		try {	
			Track trackCreated = trackService.save(track);
			return ResponseEntity.status(HttpStatus.CREATED).body(trackCreated);
		} catch(ErrorInDataException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{trackId}")
	public ResponseEntity<Track> removeTrack(@PathVariable Long trackId) {
		Optional<Track> trackFound = trackService.getTrackById(trackId);
		
		if(trackFound.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		trackService.remove(trackId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{trackId}")
	public ResponseEntity<Track> updateTrack(@PathVariable Long trackId, @RequestBody Track track) {
		Optional<Track> trackFound = trackService.getTrackById(trackId);
		
		if(trackFound.isPresent()) {		
			BeanUtils.copyProperties(track, trackFound.get(), "id");			
			try {				
				Track trackUpdated = trackService.save(trackFound.get());
				
				return ResponseEntity.ok(trackUpdated);
			} catch(EntityNotFoundException e) {
				return ResponseEntity.badRequest().build();
			}
			
		}
		
		return ResponseEntity.notFound().build();
	}
}
