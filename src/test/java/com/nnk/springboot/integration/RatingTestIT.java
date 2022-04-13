package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import com.nnk.springboot.service.AccessUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WithMockUser(username = "xGuix")
public class RatingTestIT
{
	@Autowired
	AccessUserDetailService accessUserDetailService;

	@Autowired
	RatingController ratingController;

	@Autowired
	RatingRepository ratingRepository;

	Rating rating;
	Rating ratingToTest;


	@BeforeEach
	void setupTest()
	{
		rating = new Rating();
		rating.setMoodysRating("MoodysRatingTest");
		rating.setFitchRating("FitchPRatingTest");
		rating.setSandPRating("SandPratingTest");
		rating.setOrderNumber(10);
		ratingRepository.save(rating);
	}

	@Test
	public void ratingSaveTest()
	{
		// Save
		String addRatingForm = ratingController.addRatingForm(rating);
		List<Rating> ratingListTest = ratingRepository.findAll();

		ratingToTest = ratingListTest.get(0);

		assertEquals("rating/add",addRatingForm);
		assertNotNull(ratingListTest);
		assertTrue(ratingListTest.size()==1);
		assertEquals("MoodysRatingTest", rating.getMoodysRating());
		assertEquals("FitchPRatingTest", rating.getFitchRating());
		assertEquals("SandPratingTest", rating.getSandPRating());
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