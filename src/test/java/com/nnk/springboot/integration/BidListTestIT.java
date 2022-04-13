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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WithMockUser(username = "xGuix")
class BidListTestIT
{
	@Autowired
	BidListController bidListController;

	@Autowired
	BidListRepository bidListRepository;

	BidList bid;
	BidList bidToTest;

	@BeforeEach
	void setupTest()
	{
		bid = new BidList();
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10d);
	}

	@Test
	void bidListSaveTest()
	{
		// Save
		String addBidForm = bidListController.addBidForm(bid);
		List<BidList> bidListTest = bidListRepository.findAll();

		bidToTest = bidListTest.get(0);

		assertEquals("bidList/add", addBidForm);
		assertNotNull(bidListTest);
		assertEquals(1, bidListTest.size());
		assertEquals("Account Test", bidToTest.getAccount());
		assertEquals("Type Test", bidToTest.getType());
		assertEquals(20d, bidToTest.getBidQuantity());
	}

	@Test
	void bidListUpdateTest()
	{
		// Update
		bid.setBidQuantity(20d);
		bidToTest = bidListRepository.save(bid);

		assertEquals(20d, bidToTest.getBidQuantity());
	}

	@Test
	void bidListFindTest()
	{
		// Find
		List<BidList> listResult = bidListRepository.findAll();

		assertTrue(listResult.size() == 0);
	}

	@Test
	void bidListDeleteTest()
	{
		// Delete
		List<BidList> bidListTest = bidListRepository.findAll();
		bidToTest = bidListTest.get(0);

		bidListRepository.delete(bidToTest);
		List<BidList> testResult = bidListRepository.findAll();

		assertEquals(0, testResult.size());
		assertTrue(testResult.isEmpty());
	}
}