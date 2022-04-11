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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TradeTestIT
{
	@Autowired
	AccessUserDetailService accessUserDetailService;

	@Autowired
	TradeController tradeController;

	@Autowired
	TradeRepository tradeRepository;

	Trade trade;

	@BeforeEach
	void setupTest()
	{
		trade = new Trade();
		trade.setTradeId(1);
		trade.setAccount("Trade Account Test");
		trade.setType("Type Test");
		trade.setBuyQuantity(1000d);
	}

	@Test
	void tradeSaveTest()
	{
		// Save
		tradeController.addTradeForm(trade);
		List<Trade> tradeToFind = tradeRepository.findAll();
		assertNotNull(trade.getTradeId());
		assertEquals(trade, tradeToFind.get(0));
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