package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.exception.ErrorInDataException;
import com.sansongs.sansongs.model.TypeMusic;
import com.sansongs.sansongs.repository.TypeMusicRepository;

@Service
public class TypeMusicService {

	@Autowired
	private TypeMusicRepository typeMusicRepository;
	
	public List<TypeMusic> getAllTypeMusic() {
		return typeMusicRepository.findAll();
	}
	
	public Optional<TypeMusic> getTypeMusicById(Long typeMusicId) {
		return typeMusicRepository.findById(typeMusicId);
	}
	
	public TypeMusic save(TypeMusic streaming) {
		try {
			return typeMusicRepository.save(streaming);
		} catch(DataAccessException e) {
			throw new ErrorInDataException("Property cannot be null");
		}
	}
	
	public void remove(Long typeMusicId) {
		Optional<TypeMusic> typeMusicFound = typeMusicRepository.findById(typeMusicId);
		
		try {			
			typeMusicRepository.delete(typeMusicFound.get());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException
			(String.format("TypeMusic with id %d is in use", typeMusicId));
		}
	}
}