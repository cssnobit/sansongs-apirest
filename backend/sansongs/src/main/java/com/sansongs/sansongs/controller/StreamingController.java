package com.sansongs.sansongs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
