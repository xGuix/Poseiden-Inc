package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
 * RuleName Controller class
 */
@Controller
public class RuleNameController
{
    private static final Logger logger = LogManager.getLogger("RuleNameControllerLog");

    /**
     * Load service RuleName
     */
    @Autowired
    RuleNameService ruleNameService;

    /**
     * Request rule name list page:
     * All rule name for user
     *
     * @param model Model
     * @return ruleName/list RuleName list page
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNameList", ruleNameService.findAll());
        logger.info("Get rule name list");
        return "ruleName/list";
    }

    /**
     * Get add rule name page:
     * Form for new rule name
     *
     * @param ruleName RuleName new rule name
     * @return ruleName/add New rule name page
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName)
    {
        logger.info("Get add in rule name list");
        return "ruleName/add";
    }

    /**
     * Post to validate rule name:
     * Form for new rule name
     *
     * @param ruleName RuleName ruleName
     * @param result BindingResult result
     * @param model Model ruleName
     * @return ruleName/list All ruleName list page
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model)
    {
        logger.info("Get rule name validation");
        if(!result.hasErrors())
        {
            ruleNameService.save(ruleName);
            logger.info("RuleName with id {} has been saved !", ruleName.getId());
            return "redirect:/ruleName/list";
        }
        else {
            logger.error("Error id: RuleName not validate. Please check and retry!");
            return "ruleName/add";
        }
    }

    /**
     * Get rule name update:
     * Form to update rule name
     *
     * @param id Integer id
     * @param model Model ruleName
     * @return ruleName/update Update ruleName page
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute("ruleName", ruleName);
        logger.info("Get update rule name with id");
        return "ruleName/update";
    }

    /**
     * Post rule name update:
     * Form to post update rule name
     *
     * @param id Integer id
     * @param ruleName RuleName update
     * @param result BindingResult result
     * @param model Model ruleName
     * @return redirect:/ruleName/list Update ruleName page
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model)
    {
        logger.info("Post update curve point with id");
        if(!result.hasErrors())
        {
            ruleNameService.save(ruleName);
            logger.info("Rule name with account {} has been updated !", ruleName.getId());
            return "redirect:/ruleName/list";
        }
        else {
            logger.error("Error id: update not validate. Please check and retry!");
            return "/ruleName/update/{id}";
        }

    }

    /**
     * Get rule name to delete:
     * Call to delete rule name
     *
     * @param id Integer id
     * @param model Model ruleName
     * @return redirect:/ruleName/list Update ruleName list page
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model)
    {
        logger.info("Delete rule name with id");
        ruleNameService.delete(id);
        logger.info("Rule name with id: {} deleted !", id);
        return "redirect:/ruleName/list";
    }
}