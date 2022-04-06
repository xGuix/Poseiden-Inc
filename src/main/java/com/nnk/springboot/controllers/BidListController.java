package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
 * BidList Controller class
 */
@Controller
public class BidListController
{
    private static final Logger logger = LogManager.getLogger("BidListControllerLog");

    /**
     * Load service Bid List
     */
    @Autowired
    BidListService bidListService;

    /**
     * Request bid list page:
     * All bids for user
     *
     * @param model Model
     * @return bidList/list Bid list page
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidList", bidListService.findAll());
        logger.info("Get bid list");
        return "bidList/list";
    }

    /**
     * Get add bid page:
     * Form for new bid
     *
     * @param bid BidList bid
     * @return bidList/add Add new bid page
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid)
    {
        logger.info("Get add in bid list");
        return "bidList/add";
    }

    /**
     * Post to validate bid:
     * Form for new bid
     *
     * @param bid BidList bid
     * @param result BindingResult result
     * @param model Model bidList
     * @return bidList/validate Validate bid page
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model)
    {
        logger.info("Get bid validation");
        if(!result.hasErrors())
        {
            bidListService.save(bid);
            logger.info("Bid with account {} has been saved !",bid.getAccount());
            return "redirect:/bidList/list";
        }
        else{
            logger.error("Error : bid not validate. Please check and retry!");
            return "bidList/add";
        }
    }

    /**
     * Get bid update:
     * Form to update bids
     *
     * @param id Integer id
     * @param model Model bidList
     * @return bidList/update Update bid page
     */
    @GetMapping("/bidList/update/{id}")
    public String getUpdateBid(@PathVariable("id") Integer id, Model model)
    {
        BidList bidList = bidListService.findById(id);
        model.addAttribute("bidList",bidList);
        logger.info("Get update bid with id");
        return "bidList/update";
    }

    /**
     * Post bid update:
     * Form to post update bids
     *
     * @param id Integer id
     * @param bidList BidList bidList
     * @param result BindingResult result
     * @param model Model bidList
     * @return redirect:/bidList/list Update bidList page
     */
    @PostMapping("/bidList/update/{id}")
    public String postUpdateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model)
    {
        logger.info("Post update bid with id");
        if(!result.hasErrors())
        {
            //bidList.setBidListId(id);
            bidListService.save(bidList);
            logger.info("bid with account {} has been updated !", bidList.getAccount());
            return "redirect:/bidList/list";
        }
        else {
            logger.error("Error id: update not validate. Please check and retry!");
            return "/bidList/update/{id}";
        }
    }

    /**
     * Get bid delete:
     * Call to delete bids
     *
     * @param id Integer id
     * @return redirect:/bidList/list Update bidList page
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id)
    {
        bidListService.delete(id);
        logger.info("bid with id: {} deleted !", id);
        return "redirect:/bidList/list";
    }
}