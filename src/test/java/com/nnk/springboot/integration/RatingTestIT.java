package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import com.nnk.springboot.service.AccessUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RatingTestIT
{
	@Autowired
	AccessUserDetailService accessUserDetailService;

	@Autowired
	RatingController ratingController;

	@Autowired
	RatingRepository ratingRepository;

	Rating rating;


	@BeforeEach
	void setupTest()
	{
		rating = new Rating("MoodysRatingTest", "SandPratingTest", "FitchPRatingTest", 10);
	}

	@Test
	public void ratingSaveTest()
	{
		// Save
		rating = ratingRepository.save(rating);
		assertNotNull(rating.getId());
		assertEquals(10, rating.getOrderNumber());
	}

	@Test
	public void ratingUpdateTest()
	{
		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		assertEquals(20, rating.getOrderNumber());
	}

	@Test
	public void ratingFindTest()
	{
		// Find
		List<Rating> listResult = ratingRepository.findAll();
		assertTrue(listResult.size() > 0);
	}

	@Test
	public void ratingDeleteTest()
	{
		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertFalse(ratingList.isPresent());
	}
}