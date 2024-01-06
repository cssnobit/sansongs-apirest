package com.sansongs.sansongs.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Artist {

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String imgUrl;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Country country;
	
	@ManyToMany
	@JoinTable(name = "artist_album", 
			joinColumns = @JoinColumn(name = "artist_id"), 
			inverseJoinColumns = @JoinColumn(name = "album_id"))
	private List<Album> albuns = new ArrayList<>();
	
}
