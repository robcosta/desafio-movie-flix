package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.services.validations.ReviewInsertValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ReviewInsertValid
public class ReviewDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String text;

    @NotNull(message = "Campo requerido")
    private Long movieId;    
    
    private Long userId;
    private String userName;
    private String userEmail;
    
    public ReviewDTO() {
    	
    }
    
	public ReviewDTO(Long id, String text, Long movieId, Long userId, String userName, String userEmail) {
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
	public ReviewDTO(Review entity) {
		id = entity.getId();
		this.text = entity.getText();
		this.movieId = entity.getMovie().getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getName();
		this.userEmail = entity.getUser().getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
