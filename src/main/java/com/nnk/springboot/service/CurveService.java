package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Curve Service class
 */
@Service
public class CurveService
{
    /**
     *  Load Curve Point Repository
     */
    @Autowired
    CurvePointRepository curvePointRepository;

    private static final Logger logger = LogManager.getLogger("CurveServiceLog");

    /**
     * Constructor Curve Point service
     *
     * @param curvePointRepository CurvePoint repository
     */
    public void setCurvePointRepository(CurvePointRepository curvePointRepository)
    {
        this.curvePointRepository = curvePointRepository;
    }


    /**
     * Find list of all curve points :
     * Call to find curve in repository
     *
     * @return CurvePoint The Curve Point list
     */
    public List<CurvePoint> findAll()
    {
        logger.info("Find all curve point list");
        return curvePointRepository.findAll();
    }

    /**
     * Find curve points with id:
     * Call to find a specific curve point in repository
     *
     * @param id Integer id
     * @return CurvePoint The Curve Point id
     */
    public CurvePoint findById(Integer id)
    {
        logger.info("Find curve point by id: {}", id);
        return curvePointRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("CurvePoint", "id"));
    }

    /**
     * Save curve point:
     * Call to save curve points in repository
     *
     * @param curvePoint Curve points bidList
     * @return CurvePoint The new curve point save
     */
    public CurvePoint save(CurvePoint curvePoint)
    {
        logger.info("Save curve point with id: {}", curvePoint.getCurveId());
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Delete curve point with id:
     * Call to delete curve point in repository
     *
     * @param id Integer Curve id
     * Void No return
     */
    public void delete(Integer id)
    {
        CurvePoint curvePointToDelete = findById(id);
        logger.info("Delete curve point with id: {}", curvePointToDelete.getCurveId());
        curvePointRepository.delete(curvePointToDelete);
    }
}