package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.AccessUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BidListTestIT
{
	@Autowired
	AccessUserDetailService accessUserDetailService;

	@Autowired
	BidListController bidListController;

	@Autowired
	BidListRepository bidListRepository;

	BidList bid = new BidList();

	@BeforeEach
	void setupTest()
	{
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10d);
	}

	@Test
	void bidListSaveTest()
	{
		// Save
		String addBidForm = bidListController.addBidForm(bid);
		assertEquals("bidList/add", addBidForm);

		bidListRepository.save(bid);
		assertNotNull(bid.getBidListId());

		List<BidList> testList = bidListRepository.findAll();
		assertNotNull(bidListRepository.findAll());
	}

	@Test
	void bidListUpdateTest()
	{
		// Update
		bid.setBidQuantity(20d);
		bidListController.getUpdateBid(bid.getBidListId(), (Model) bid);
		assertEquals(bid.getBidQuantity(), 20d, 20d);
	}

	@Test
	void bidListFindTest()
	{
		// Find
		List<BidList> listResult;
		listResult = bidListRepository.findAll();
		assertTrue(listResult.size() > 0);
	}

	@Test
	void bidListDeleteTest()
	{
		// Delete
		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		List<BidList> bidList = bidListRepository.findAll();
		assertNotEquals(bidList.get(bid.getBidListId()), bid);
		assertFalse(bidListRepository.existsById(id));
	}
}