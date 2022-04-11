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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CurvePointTestIT
{
	@Autowired
	private AccessUserDetailService accessUserDetailService;

	@Autowired
	CurveController curveController;

	@Autowired
	CurvePointRepository curvePointRepository;

	CurvePoint curvePoint;

	@BeforeEach
	void setupTest()
	{
		curvePoint = new CurvePoint(10, 10d, 30d);
	}

	@Test
	void curvePointSaveTest()
	{
		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		assertNotNull(curvePoint.getId());
		assertEquals(10, curvePoint.getCurveId());
	}

	@Test
	void curvePointUpdateTest()
	{
		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		assertEquals(20, curvePoint.getCurveId());
	}

	@Test
	void curvePointFindTest()
	{
		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertTrue(listResult.size() > 0);
	}

	@Test
	void curvePointDeleteTest()
	{
		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertFalse(curvePointList.isPresent());
	}
}