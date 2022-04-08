package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BidList Service class
 */
@Service
public class BidListService
{
    /**
     *  Load Bid List Repository
     */
    @Autowired
    BidListRepository bidListRepository;

    private static final Logger logger = LogManager.getLogger("BidListServiceLog");

    /**
     * Find list of all bids :
     * Call to find bids in repository
     *
     * @return eturn BidList The Bids list
     */
    public List<BidList> findAll()
    {
        logger.info("Find All Bids List");
        return bidListRepository.findAll();
    }

    /**
     * Find bids with id:
     * Call to find a specific bid in repository
     *
     * @param id Integer id
     * @return BidList The Bids id
     */
    public BidList findById(Integer id)
    {
        logger.info("Find BidList By id: {}", id);
        return bidListRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("BidList", "id"));
    }

    /**
     * Save new bid:
     * Call to save bids in repository
     *
     * @param bidList BidList bidList
     * @return BidList The new bid save
     */
    public BidList save(@NotNull BidList bidList)
    {
        logger.info("Save BidList with account name: {}", bidList.getAccount());
        return bidListRepository.save(bidList);
    }

    /**
     * Delete bid with id:
     * Call to delete bids in repository
     *
     * @param id Integer bid id
     * @return Void No return
     */
    public void delete(Integer id)
    {
        BidList bidListToDelete = findById(id);
        logger.info("Delete BidList with account name: {}", bidListToDelete.getAccount());
        bidListRepository.delete(bidListToDelete);
    }
}
