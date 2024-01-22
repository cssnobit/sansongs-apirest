package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.exception.ErrorInDataException;
import com.sansongs.sansongs.model.Track;
import com.sansongs.sansongs.repository.ArtistRepository;
import com.sansongs.sansongs.repository.TrackRepository;

@Service
public class TrackService {

	@Autowired
	private TrackRepository trackRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	public List<Track> getAllTracks(){
		return trackRepository.findAll();
	}
	
	public Optional<Track> getTrackById(Long trackId) {
		return trackRepository.findById(trackId);
	}
	
	public Track save(Track track) {
		try {			
			return trackRepository.save(track);
		} catch(DataAccessException e) {
			throw new ErrorInDataException("Property cannot be null or association id not exists");
		}
	}
	
	public void remove(Long trackId) {
		Optional<Track> trackFound = trackRepository.findById(trackId);
		
		trackRepository.delete(trackFound.get());
	}
}
