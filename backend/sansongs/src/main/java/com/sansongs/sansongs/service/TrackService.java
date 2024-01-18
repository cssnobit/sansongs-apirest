package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.model.Artist;
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
		Set<Artist> artists = track.getArtists();

		return trackRepository.save(track);
	}
	
	public void remove(Long trackId) {
		Optional<Track> trackFound = trackRepository.findById(trackId);
		
		trackRepository.delete(trackFound.get());
	}
}
