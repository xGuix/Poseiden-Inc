package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Rating Controller class
 */
@Controller
public class RatingController
{
    private static final Logger logger = LogManager.getLogger("RatingControllerLog");

    /**
     * Load service Rating
     */
    @Autowired
    RatingService ratingService;

    /**
     * Request rating list page:
     * All rating for user
     *
     * @param model Model
     * @return rating/list Rating list page
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratingList", ratingService.findAll());
        logger.info("Get rating list");
        return "rating/list";
    }

    /**
     * Get add rating page:
     * Form for new rating
     *
     * @param rating Rating new rating
     * @return rating/add New rating page
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating)
    {
        logger.info("Get add in rating list");
        return "rating/add";
    }

    /**
     * Post to validate rating:
     * Form for new rating
     *
     * @param rating Rating rating
     * @param result BindingResult result
     * @param model Model rating
     * @return rating/validate Validate rating page
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model)
    {
        logger.info("Get rating validation");
        if(!result.hasErrors())
        {
            ratingService.save(rating);
            logger.info("Rating with id {} has been saved !", rating.getId());
            return "redirect:/rating/list";
        }
        else {
            logger.error("Error id: Rating not validate. Please check and retry!");
            return "rating/add";
        }
    }

    /**
     * Get rating update:
     * Form to update rating
     *
     * @param id Integer id
     * @param model Model rating
     * @return rating/update Update rating page
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        logger.info("Get update rating with id");
        return "rating/update";
    }

    /**
     * Post rating update:
     * Form to post update rating
     *
     * @param id Integer id
     * @param rating Rating update
     * @param result BindingResult result
     * @param model Model curvePoint
     * @return redirect:/rating/list Update rating page
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model)
    {
        logger.info("Post update curve point with id");
        if(!result.hasErrors())
        {
            ratingService.save(rating);
            logger.info("Curve point with account {} has been updated !", rating.getId());
            return "redirect:/rating/list";
        }
        else {
            logger.error("Error id: update not validate. Please check and retry!");
            return "/rating/update/{id}";
        }
    }

    /**
     * Get rating to delete:
     * Call to delete rating
     *
     * @param id Integer id
     * @param model Model rating
     * @return redirect:/rating/list Update rating page
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model)
    {
        logger.info("Delete rating with id");
        ratingService.delete(id);
        logger.info("Rating with id: {} deleted !", id);
        return "redirect:/rating/list";
    }
}