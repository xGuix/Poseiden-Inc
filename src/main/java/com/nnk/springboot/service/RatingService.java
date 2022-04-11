package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Rating Service class
 */
@Service
public class RatingService
{
    /**
     *  Load Rating Repository
     */
    @Autowired
    RatingRepository ratingRepository;

    private static final Logger logger = LogManager.getLogger("RatingServiceLog");


    /**
     * Constructor Rating service
     *
     * @param ratingRepository BidList repository
     */
    public void setRatingRepository(RatingRepository ratingRepository)
    {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Find list of all ratings :
     * Call to find rating in repository
     *
     * @return ratingList The ratings list
     */
    public List<Rating> findAll()
    {
        logger.info("Find all rating list");
        return ratingRepository.findAll();
    }

    /**
     * Find rating with id:
     * Call to find a specific rating in repository
     *
     * @param id Integer id
     * @return Rating The rating id
     */
    public Rating findById(Integer id)
    {
        logger.info("Find rating by id: {}", id);
        return ratingRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Rating", "id"));
    }

    /**
     * Save rating:
     * Call to save ratings in repository
     *
     * @param rating Rating rating
     * @return Rating The new rating save
     */
    public Rating save(Rating rating)
    {
        logger.info("Save rating with id: {}", rating.getId());
        return ratingRepository.save(rating);
    }

    /**
     * Delete rating with id:
     * Call to delete rating in repository
     *
     * @param id Integer rating id
     * Void No return
     */
    public void delete(Integer id)
    {
        Rating ratingToDelete = findById(id);
        logger.info("Delete curve point with id: {}", ratingToDelete.getId());
        ratingRepository.delete(ratingToDelete);
    }
}