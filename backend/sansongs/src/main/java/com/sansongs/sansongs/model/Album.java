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
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Album {

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String imgUrl;
	
	@Column(nullable = false)
	private Integer year;
	
	@OneToMany(mappedBy = "album")
	@Column(nullable = false)
	private List<Track> tracks = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "artist_album", 
			joinColumns = @JoinColumn(name = "artist_id"), 
			inverseJoinColumns = @JoinColumn(name = "album_id"))
	@Column(nullable = false)
	private List<Artist> artists = new ArrayList<>();
	
}