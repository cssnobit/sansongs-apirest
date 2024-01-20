package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.model.Artist;
import com.sansongs.sansongs.repository.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	
	public List<Artist> getAllArtists() {
		return artistRepository.findAll();
	}
	
	public Optional<Artist> getArtistById(Long artistId) {
		return artistRepository.findById(artistId);
	}
}
