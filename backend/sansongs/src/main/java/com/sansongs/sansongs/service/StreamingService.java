package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.model.Streaming;
import com.sansongs.sansongs.repository.StreamingRepository;

@Service
public class StreamingService {

	@Autowired
	private StreamingRepository streamingRepository;
	
	public List<Streaming> getAllStreaming() {
		return streamingRepository.findAll();
	}
	
	public Optional<Streaming> getStreamingById(Long streamingId) {
		return streamingRepository.findById(streamingId);
	}
}
