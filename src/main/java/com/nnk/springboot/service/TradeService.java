package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Trade Service class
 */
@Service
@Transactional
public class TradeService
{
    /**
     *  Load Trade Repository
     */
    @Autowired
    TradeRepository tradeRepository;

    private static final Logger logger = LogManager.getLogger("TradeServiceLog");

    /**
     * Find list of all trade :
     * Call to find trade in repository
     *
     * @return tradeList The trade list
     */
    public List<Trade> findAll()
    {
        logger.info("Find all trade list");
        return tradeRepository.findAll();
    }

    /**
     * Find trade with id:
     * Call to find a specific trade in repository
     *
     * @param id Integer id
     * @return Trade The trade id
     */
    public Trade findById(Integer id)
    {
        logger.info("Find trade by id: {}", id);
        return tradeRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("trade", "id"));
    }

    /**
     * Save trade:
     * Call to save trade in repository
     *
     * @param trade Trade new trade
     * @return Trade The new trade save
     */
    public Trade save(Trade trade)
    {
        logger.info("Save rating with id: {}", trade.getTradeId());
        return tradeRepository.save(trade);
    }

    /**
     * Delete trade with id:
     * Call to delete trade in repository
     *
     * @param id Integer trade id
     * @Return Void No return
     */
    public void delete(Integer id)
    {
        Trade tradeToDelete = findById(id);
        logger.info("Delete curve point with id: {}", tradeToDelete.getTradeId());
        tradeRepository.delete(tradeToDelete);
    }
}