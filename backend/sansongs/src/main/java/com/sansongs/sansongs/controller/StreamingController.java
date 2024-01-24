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
import com.sansongs.sansongs.model.Streaming;
import com.sansongs.sansongs.service.StreamingService;

@RequestMapping("/streamings")
@RestController
public class StreamingController {
	
	@Autowired
	private StreamingService streamingService;

	@GetMapping
	public List<Streaming> findAllStreamings() {
		return streamingService.getAllStreaming();
	}
	
	@GetMapping("/{streamingId}")
	public ResponseEntity<Streaming> findStreamingById(@PathVariable Long streamingId) {
		Optional<Streaming> streamingFound = streamingService.getStreamingById(streamingId);
		
		if(streamingFound.isPresent()) {
			return ResponseEntity.ok(streamingFound.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Streaming> addStreaming(@RequestBody Streaming streaming) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(streamingService.save(streaming));
		} catch(ErrorInDataException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{streamingId}")
	public ResponseEntity<Streaming> removeStreaming(@PathVariable Long streamingId) {
		Optional<Streaming> streamingFound = streamingService.getStreamingById(streamingId);
		
		if(streamingFound.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			streamingService.remove(streamingId);
			return ResponseEntity.noContent().build();
		} catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping("/{streamingId}")
	public ResponseEntity<Streaming> updateArtist(@PathVariable Long streamingId,
			@RequestBody Streaming streaming) {
		
		Optional<Streaming> streamingFound = streamingService.getStreamingById(streamingId);
		
		if(streamingFound.isPresent()) {
			BeanUtils.copyProperties(streaming, streamingFound.get(), "id");
			try {				
				Streaming streamingUpdated = streamingService.save(streamingFound.get());
				
				return ResponseEntity.ok(streamingUpdated);
			} catch(ErrorInDataException e) {
				return ResponseEntity.badRequest().build();
			}
		}
		
		return ResponseEntity.notFound().build();
	}
}
