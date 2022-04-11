package com.nnk.springboot.domain;

import groovy.transform.Generated;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "curve_point")
@Generated
public class CurvePoint
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @Column(name = "term")
    private Double term;

    @Column(name = "value")
    private Double value;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Timestamp creationDate;

    public CurvePoint()
    {}

    public CurvePoint(Integer curveId, double term, double value)
    {}

    public CurvePoint(Integer id,
                      Integer curveId,
                      Timestamp asOfDate,
                      Double term,
                      Double value,
                      Timestamp creationDate)
    {
        this.id = id;
        this.curveId = curveId;
        this.asOfDate = asOfDate;
        this.term = term;
        this.value = value;
        this.creationDate = creationDate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCurveId()
    {
        return curveId;
    }

    public void setCurveId(Integer curveId)
    {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate()
    {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate)
    {
        this.asOfDate = asOfDate;
    }

    public Double getTerm()
    {
        return term;
    }

    public void setTerm(Double term)
    {
        this.term = term;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue(Double value)
    {
        this.value = value;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }
}