package com.sansongs.sansongs.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "\"track\"")
public class Track {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Integer yearRelease;
	
	@Column(precision = 2, scale = 1)
	private BigDecimal avgRating;
	
	@ManyToMany
	@JoinTable(name = "\"song\"", joinColumns = @JoinColumn(name = "track_id"),
			inverseJoinColumns = @JoinColumn(name = "artist_id"))
			@Column(nullable = false)
	private Set<Artist> artists = new HashSet<>();
	
	@JsonIgnore
	@ManyToOne
	private Album album;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "track_streaming", 
			joinColumns = @JoinColumn(name = "track_id"), 
			inverseJoinColumns = @JoinColumn(name = "streaming_id"))
	private Set<Streaming> streamings = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private TypeMusic typeMusic;
	
	private String imgUrl;
}
