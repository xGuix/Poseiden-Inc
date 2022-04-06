package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BidListTestsIT
{
	@Autowired
	private BidListController bidListController;

	@Autowired
	private BidListService bidListService;

	BidList bid;

	@BeforeEach
	public void setupTest()
	{
		bid = new BidList("Account Test", "Type Test", 10d);
	}

	@Test
	public void bidListSaveTest()
	{
		// Save
		bidListController.addBidForm(bid);

		bid = bidListService.save(bid);

		assertNotNull(bid);
	}

	@Test
	public void bidListUpdateTest()
	{
		// Update
		bid.setBidQuantity(20d);
		bidListController.getUpdateBid(bid.getBidListId(), (Model) bid);
		assertEquals(bid.getBidQuantity(), 20d, 20d);
	}

	@Test
	public void bidListFindTest()
	{
		// Find
		List<BidList> listResult = bidListService.findAll();
		assertTrue(listResult.size() > 0);
	}

	@Test
	public void bidListDeleteTest()
	{
		// Delete
		Integer id = bid.getBidListId();
		bidListController.deleteBid(id);
		BidList bidList = bidListService.findById(id);
		assertNull(bidList);
	}
}
