package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CurveServiceTest
{
    @MockBean
    CurvePointRepository curvePointRepository;

    @Autowired
    CurveService curveService;

    CurvePoint curvePoint;

    @BeforeEach
    void setUp()
    {
        curveService.setCurvePointRepository(curvePointRepository);
        curvePoint = new CurvePoint(10,10d,100d);
    }

    @Test
    void findAllTest()
    {
        curveService.findAll();

        verify(curvePointRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdTest()
    {
        Mockito.when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        curveService.findById(1);

        verify(curvePointRepository, Mockito.times(1)).findById(1);
        assertEquals(curvePoint, curveService.findById(1));
    }

    @Test
    void findByIdExceptionTest()
    {
        assertThrows(ObjectNotFoundException.class,()->curveService.findById(1));
    }

    @Test
    void saveTest()
    {
        curveService.save(curvePoint);

        verify(curvePointRepository, Mockito.times(1)).save(curvePoint);
    }

    @Test
    void deleteTest()
    {
        Mockito.when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        curveService.delete(1);

        verify(curvePointRepository, Mockito.times(1)).delete(curvePoint);
    }
}