package com.sansongs.sansongs.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Track {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Integer year;
	
	@Column(precision = 2, scale = 1)
	private BigDecimal avgRating;
	
	@ManyToMany
	@JoinTable(name = "song", joinColumns = @JoinColumn(name = "track_id"),
			inverseJoinColumns = @JoinColumn(name = "artist_id"))
			@Column(nullable = false)
	private List<Artist> artists = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	private Album album;
	
	@ManyToMany
	@JoinTable(name = "track_streaming", 
			joinColumns = @JoinColumn(name = "track_id"), 
			inverseJoinColumns = @JoinColumn(name = "streaming_id"))
	private List<Streaming> streamings = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private TypeMusic typeMusic;
	
	private String imgUrl;
}
