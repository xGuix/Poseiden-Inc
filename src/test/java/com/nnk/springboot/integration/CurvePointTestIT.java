package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WithMockUser(username = "xGuix")
public class CurvePointTestIT
{
	@Autowired
	private AccessUserDetailService accessUserDetailService;

	@Autowired
	CurveController curveController;

	@Autowired
	CurvePointRepository curvePointRepository;

	CurvePoint curvePoint;
	CurvePoint curveToTest;

	@BeforeEach
	void setupTest()
	{
		curvePoint = new CurvePoint();
		curvePoint.setCurveId(10);
		curvePoint.setTerm(10d);
		curvePoint.setValue(30d);
	}

	@Test
	void curvePointSaveTest()
	{
		// Save
		String addCurveForm = curveController.addBidForm(curvePoint);
		List<CurvePoint> curvePointList = curvePointRepository.findAll();

		curveToTest = curvePointList.get(0);

		assertEquals("curvePoint/add", addCurveForm);
		assertNotNull(curvePointList);
		assertTrue(curvePointList.size()==1);
		assertEquals(20, curveToTest.getCurveId());
		assertEquals(10d, curveToTest.getTerm());
		assertEquals(30d, curveToTest.getValue());
	}

	@Test
	void curvePointUpdateTest()
	{
		// Update
		curvePoint.setCurveId(20);
		curveToTest = curvePointRepository.save(curvePoint);

		assertEquals(20, curvePoint.getCurveId());
	}

	@Test
	void curvePointFindTest()
	{
		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();

		assertTrue(listResult.size() == 0);
	}

	@Test
	void curvePointDeleteTest()
	{
		// Delete
		curvePointRepository.save(curvePoint);
		List<CurvePoint> curveListTest = curvePointRepository.findAll();
		curveToTest = curveListTest.get(0);

		curvePointRepository.delete(curveToTest);
		List<CurvePoint> testResult = curvePointRepository.findAll();

		assertEquals(0, testResult.size());
		assertTrue(testResult.isEmpty());
	}
}