package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RuleName Service class
 */
@Service
public class RuleNameService
{
    /**
     *  Load RuleName Repository
     */
    @Autowired
    RuleNameRepository ruleNameRepository;

    private static final Logger logger = LogManager.getLogger("RuleNameServiceLog");

    /**
     * Constructor RuleName service
     *
     * @param ruleNameRepository RuleName repository
     */
    public void setRuleNameRepository(RuleNameRepository ruleNameRepository)
    {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * Find list of all rule name :
     * Call to find rule name in repository
     *
     * @return ruleNameList The ratings list
     */
    public List<RuleName> findAll()
    {
        logger.info("Find all rule name list");
        return ruleNameRepository.findAll();
    }

    /**
     * Find rule name with id:
     * Call to find a specific rule name in repository
     *
     * @param id Integer id
     * @return RuleName The ruleNames id
     */
    public RuleName findById(Integer id)
    {
        logger.info("Find rating by id: {}", id);
        return ruleNameRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("RuleName", "id"));
    }

    /**
     * Save rule name:
     * Call to save rule name in repository
     *
     * @param ruleName RuleName ruleName
     * @return Rating The new rating save
     */
    public RuleName save(RuleName ruleName)
    {
        logger.info("Save rating with id: {}", ruleName.getId());
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Delete rule name with id:
     * Call to delete rule name in repository
     *
     * @param id Integer ruleName id
     * Void No return
     */
    public void delete(Integer id)
    {
        RuleName ruleNameToDelete = findById(id);
        logger.info("Delete rule name with id: {}", ruleNameToDelete.getId());
        ruleNameRepository.delete(ruleNameToDelete);
    }
}