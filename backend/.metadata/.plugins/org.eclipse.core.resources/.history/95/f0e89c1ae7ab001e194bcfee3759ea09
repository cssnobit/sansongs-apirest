package com.sansongs.sansongs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sansongs.sansongs.model.Track;
import com.sansongs.sansongs.repository.TrackRepository;

@RestController
@RequestMapping("/tracks")
public class TrackController {
	
	@Autowired
	private TrackRepository trackRepository;
	
	@GetMapping
	public List<Track> findAll(){
		return trackRepository.findAll();
	}
}
