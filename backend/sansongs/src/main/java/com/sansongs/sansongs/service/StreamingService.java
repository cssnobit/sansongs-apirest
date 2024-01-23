package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.exception.ErrorInDataException;
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
	
	public Streaming save(Streaming streaming) {
		try {
			return streamingRepository.save(streaming);
		} catch(DataAccessException e) {
			throw new ErrorInDataException("Property cannot be null");
		}
	}
	
	public void remove(Long streamingId) {
		Optional<Streaming> streamingFound = streamingRepository.findById(streamingId);
		
		try {			
			streamingRepository.delete(streamingFound.get());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException
			(String.format("Streaming with id %d cannot be removed", streamingId));
		}
	}
}
