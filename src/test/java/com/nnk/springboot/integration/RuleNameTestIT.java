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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WithMockUser(username = "xGuix")
public class RuleNameTestIT
{
	@Autowired
	AccessUserDetailService accessUserDetailService;

	@Autowired
	RuleNameController ruleNameController;

	@Autowired
	RuleNameRepository ruleNameRepository;

	RuleName rule;
	RuleName ruleToTest;

	@BeforeEach
	void setupTest()
	{
		rule = new RuleName();
		rule.setName("Rule Name Test");
		rule.setDescription("Description Test");
		rule.setJson("Json Test");
		rule.setTemplate("Template Test");
		rule.setSqlStr("SQL Test");
		rule.setSqlPart("SQL Part Test");
		ruleNameRepository.save(rule);
	}

	@Test
	void ruleNameSaveTest()
	{
		// Save
		String addRuleForm = ruleNameController.addRuleForm(rule);
		List<RuleName> ruleListTest = ruleNameRepository.findAll();

		ruleToTest = ruleListTest.get(0);

		assertEquals("ruleName/add", addRuleForm);
		assertNotNull(ruleListTest);
		assertTrue(ruleListTest.size()>1);
		assertEquals("Rule Name Update", ruleToTest.getName());
		assertEquals("Description Test", ruleToTest.getDescription());
		assertEquals("Json Test", ruleToTest.getJson());
		assertEquals("Template Test", ruleToTest.getTemplate());
		assertEquals("SQL Test", ruleToTest.getSqlStr());
		assertEquals("SQL Part Test", ruleToTest.getSqlPart());
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
		assertFalse(ruleList.isPresent());
	}
}