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
import com.sansongs.sansongs.model.Artist;
import com.sansongs.sansongs.model.Country;
import com.sansongs.sansongs.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping
	public List<Country> findAllCountries() {
		return countryService.getAllCountries();
	}
	
	@GetMapping("/{countryId}")
	public ResponseEntity<Country> findCountryById(@PathVariable Long countryId) {
		Optional<Country> countryFound = countryService.getCountryById(countryId);
		
		if(countryFound.isPresent()) {
			return ResponseEntity.ok(countryFound.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
