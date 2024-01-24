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
import com.sansongs.sansongs.model.Streaming;
import com.sansongs.sansongs.model.TypeMusic;
import com.sansongs.sansongs.service.TypeMusicService;

@RequestMapping("/typemusics")
@RestController
public class TypeMusicController {
	
	@Autowired
	private TypeMusicService typeMusicService;

	@GetMapping
	public List<TypeMusic> findAllTypeMusic() {
		return typeMusicService.getAllTypeMusic();
	}
	
	@GetMapping("/{typeMusicId}")
	public ResponseEntity<TypeMusic> findTypeMusicById(@PathVariable Long typeMusicId) {
		Optional<TypeMusic> typeMusicFound = typeMusicService.getTypeMusicById(typeMusicId);
		
		if(typeMusicFound.isPresent()) {
			return ResponseEntity.ok(typeMusicFound.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<TypeMusic> addTypeMusic(@RequestBody TypeMusic typeMusic) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(typeMusicService.save(typeMusic));
		} catch(ErrorInDataException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{typeMusicId}")
	public ResponseEntity<Streaming> removeTypeMusic(@PathVariable Long typeMusicId) {
		Optional<TypeMusic> typeMusicFound = typeMusicService.getTypeMusicById(typeMusicId);
		
		if(typeMusicFound.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			typeMusicService.remove(typeMusicId);
			return ResponseEntity.noContent().build();
		} catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
