package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.model.Track;
import com.sansongs.sansongs.repository.TrackRepository;

@Service
public class TrackService {

	@Autowired
	private TrackRepository trackRepository;
	
	public List<Track> getAllTracks(){
		return trackRepository.findAll();
	}
	
	public Optional<Track> getTrackById(Long trackId) {
		return trackRepository.findById(trackId);
	}
	
	public Track save(Track track) {
		return trackRepository.save(track);
	}
}
