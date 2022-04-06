package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;
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
 * Curve Controller class
 */
@Controller
public class CurveController
{
    private static final Logger logger = LogManager.getLogger("CurveControllerLog");

    /**
     * Load service Curve List
     */
    @Autowired
    CurveService curveService;

    /**
     * Request curve point list page:
     * All curve for user
     *
     * @param model Model
     * @return curvePoint/list CurvePoint list page
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        model.addAttribute("curveList", curveService.findAll());
        logger.info("Get curve point list");
        return "curvePoint/list";
    }

    /**
     * Get add curve point page:
     * Form for new curve point
     *
     * @param bid CurvePoint bid
     * @return curvePoint/add New CurvePoint page
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid)
    {
        logger.info("Get add in curve point list");
        return "curvePoint/add";
    }

    /**
     * Post to validate curve point:
     * Form for new curve point
     *
     * @param curvePoint CurvePoint curvePoint
     * @param result BindingResult result
     * @param model Model bidList
     * @return curvePoint/validate Validate curvePoint page
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model)
    {
        logger.info("Get curve point validation");
        if(!result.hasErrors())
        {
            curveService.save(curvePoint);
            logger.info("Curve point with id {} has been saved !", curvePoint.getCurveId());
            return "redirect:/curvePoint/list";
        }
        else{
            logger.error("Error : Curve not validate. Please check and retry!");
            return "curvePoint/add";
        }
    }

    /**
     * Get curve point update:
     * Form to update curve point
     *
     * @param id Integer id
     * @param model Model curvePoint
     * @return curvePoint/update Update curvePoint page
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        CurvePoint curvePoint = curveService.findById(id);
        model.addAttribute("curvePoint", curvePoint);
        logger.info("Get update curve point with id");
        return "curvePoint/update";
    }

    /**
     * Post curve point update:
     * Form to post update curve point
     *
     * @param id Integer id
     * @param curvePoint CurvePoint curvePoint
     * @param result BindingResult result
     * @param model Model curvePoint
     * @return redirect:/curvePoint/list Update curvePoint page
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model)
    {
        logger.info("Post update curve point with id");
        if(!result.hasErrors())
        {
            //curvePoint.setId(id);
            curveService.save(curvePoint);
            logger.info("Curve point with account {} has been updated !", curvePoint.getCurveId());
            return "redirect:/curvePoint/list";
        }
        else {
            logger.error("Error id: update not validate. Please check and retry!");
            return "/curvePoint/update/{id}";
        }
    }

    /**
     * Get curve point to delete:
     * Call to delete curve point
     *
     * @param id Integer id
     * @param model Model curvePoint
     * @return redirect:/curvePoint/list Update curvePoint page
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model)
    {
        logger.info("Delete curve point with id");
        curveService.delete(id);
        logger.info("Curve with id: {} deleted !", id);
        return "redirect:/curvePoint/list";
    }
}