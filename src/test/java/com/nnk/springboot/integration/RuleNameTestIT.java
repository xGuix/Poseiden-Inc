package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import com.nnk.springboot.service.AccessUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RuleNameTestIT
{
	@Autowired
	AccessUserDetailService accessUserDetailService;

	@Autowired
	RuleNameController ruleNameController;

	@Autowired
	RuleNameRepository ruleNameRepository;

	RuleName rule;

	@BeforeEach
	void setupTest()
	{
		rule = new RuleName("Rule Name Test", "Description Test", "Json Test", "Template Test", "SQL Test", "SQL Part Test");
	}

	@Test
	void ruleNameSaveTest()
	{
		// Save
		ruleNameRepository.save(rule);
		assertNotNull(rule.getId());
		assertEquals(ruleNameRepository.getById(rule.getId()), rule);
	}

	@Test
	void ruleNameUpdateTest()
	{
		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameRepository.save(rule);
		assertEquals("Rule Name Update", rule.getName());
	}

	@Test
	void ruleNameFindTest()
	{
		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		assertTrue(listResult.size() > 0);
	}

	@Test
	void ruleNameDeleteTest()
	{
		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		assertTrue(ruleList.isPresent());
	}
}