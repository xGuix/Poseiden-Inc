package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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

@Controller
public class TradeController
{
    private static final Logger logger = LogManager.getLogger("TradeControllerLog");
    /**
     * Load service Trade
     */
    @Autowired
    TradeService tradeService;

    /**
     * Request trade list page:
     * All trade for user
     *
     * @param model Model
     * @return trade/list Trade list page
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("tradeList", tradeService.findAll());
        logger.info("Get trade list");
        return "trade/list";
    }

    /**
     * Get add trade page:
     * Form for new trade
     *
     * @param trade Trade new trade
     * @return trade/add New trade page
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade)
    {
        logger.info("Get add in trade list");
        return "trade/add";
    }

    /**
     * Post to validate trade:
     * Form for new trade
     *
     * @param trade Trade new trade
     * @param result BindingResult result
     * @param model Model trade
     * @return trade/list All trade list page
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model)
    {
        logger.info("Get trade validation");
        if(!result.hasErrors())
        {
            tradeService.save(trade);
            logger.info("Trade with id {} has been saved !", trade.getTradeId());
            return "redirect:/trade/list";
        }
        else {
            logger.error("Error id: trade not validate. Please check and retry!");
            return "trade/add";
        }
    }

    /**
     * Get trade update:
     * Form to update trade
     *
     * @param id Integer id
     * @param model Model trade
     * @return trade/update Trade update page
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);
        logger.info("Get update trade with id");
        return "trade/update";
    }

    /**
     * Post trade update:
     * Form to post update trade
     *
     * @param id Integer id
     * @param trade Trade update
     * @param result BindingResult result
     * @param model Model trade
     * @return redirect:/trade/list Update trade list page
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model)
    {
        logger.info("Post update trade with id");
        if(!result.hasErrors())
        {
            tradeService.save(trade);
            logger.info("Trade with account {} has been updated !", trade.getTradeId());
            return "redirect:/trade/list";
        }
        else {
            logger.error("Error id: update not validate. Please check and retry!");
            return "/trade/update/{id}";
        }
    }

    /**
     * Get trade to delete:
     * Call to delete trade
     *
     * @param id Integer id
     * @param model Model trade
     * @return redirect:/trade/list Update trade page
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model)
    {
        logger.info("Delete trade with id");
        tradeService.delete(id);
        logger.info("Trade with id: {} deleted !", id);
        return "redirect:/trade/list";
    }
}