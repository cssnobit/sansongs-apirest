package com.sansongs.sansongs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sansongs.sansongs.exception.ErrorInDataException;
import com.sansongs.sansongs.model.Country;
import com.sansongs.sansongs.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}
	
	public Optional<Country> getCountryById(Long countryId) {
		return countryRepository.findById(countryId);
	}
	
	public Country save(Country country) {
		try {			
			return countryRepository.save(country);
		} catch(DataAccessException e) {
			throw new ErrorInDataException("Property cannot be null or association id not exists");
		}
	}
	
	public void remove(Long countryId) {
		Optional<Country> countryFound = countryRepository.findById(countryId);
		try {
			countryRepository.delete(countryFound.get());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException
			(String.format("Country with id %d is in use", countryId));
		}
	}
}
