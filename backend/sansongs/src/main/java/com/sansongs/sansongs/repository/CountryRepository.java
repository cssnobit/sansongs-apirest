package com.sansongs.sansongs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sansongs.sansongs.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
