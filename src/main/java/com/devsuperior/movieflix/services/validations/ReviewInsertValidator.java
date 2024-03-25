package com.devsuperior.movieflix.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.movieflix.controllers.exceptions.FieldMessage;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReviewInsertValidator implements ConstraintValidator<ReviewInsertValid, ReviewDTO> {
	
	@Autowired
	private MovieRepository repository;
	
	@Override
	public void initialize(ReviewInsertValid ann) {
	}

	@Override
	public boolean isValid(ReviewDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		Optional<Movie> result = repository.findById(dto.getMovieId());
			
		if(result.isEmpty()) {
			list.add(new FieldMessage("movieId", "Movie not found"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}