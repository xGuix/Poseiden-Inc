package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import com.nnk.springboot.service.AccessUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WithMockUser(username = "xGuix")
public class TradeTestIT
{
	@Autowired
	AccessUserDetailService accessUserDetailService;

	@Autowired
	TradeController tradeController;

	@Autowired
	TradeRepository tradeRepository;

	Trade trade;
	Trade tradeToTest;

	@BeforeEach
	void setupTest()
	{
		trade = new Trade();
		trade.setTradeId(1);
		trade.setAccount("Trade Account Test");
		trade.setType("Type Test");
		trade.setBuyQuantity(1000d);
		tradeRepository.save(trade);
	}

	@Test
	void tradeSaveTest()
	{
		// Save
		String addTradeForm = tradeController.addTradeForm(trade);
		List<Trade> tradeListTest = tradeRepository.findAll();

		tradeToTest = tradeListTest.get(0);

		assertEquals("trade/add", addTradeForm);
		assertNotNull(tradeListTest);
		assertTrue(tradeListTest.size() > 0);
		assertEquals("Trade Account Test", tradeToTest.getAccount());
		assertEquals("Type Test", tradeToTest.getType());
		assertEquals(1000d, tradeToTest.getBuyQuantity());
	}

	@Test
	void tradeUpdateTest()
	{
		// Update
		trade.setAccount("Trade Account Update");
		tradeRepository.save(trade);
		assertEquals("Trade Account Update", trade.getAccount());
	}

	@Test
	void tradeFindTest()
	{
		// Find
		List<Trade> listResult = tradeRepository.findAll();
		assertTrue(listResult.size() > 0);
	}

	@Test
	void tradeDeleteTest()
	{
		// Delete
		Integer id = trade.getTradeId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		assertFalse(tradeList.isPresent());
	}
}