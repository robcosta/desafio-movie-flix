package com.devsuperior.movieflix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id) {
		Movie entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		
				
		return new MovieDetailsDTO(entity) ;
	}

	@Transactional(readOnly = true)
	public Page<MovieCardDTO> findByGenre(String genreId, Pageable pageable) {
		
		List<Long> genreIds = new ArrayList<>();
		if (!"0".equals(genreId)) {
			genreIds.add(Long.parseLong(genreId));
		} else {
			List<Genre> genries = genreRepository.findAll();
			for(int i=0; i<genries.size(); i++) {
				genreIds.add(genries.get(i).getId());
			}
		}		
		Page<Movie> page = repository.findByGenre(genreIds, pageable);
		return page.map(x -> new MovieCardDTO(x));
	}

}
