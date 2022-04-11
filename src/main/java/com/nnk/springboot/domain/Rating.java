package com.nnk.springboot.domain;

import groovy.transform.Generated;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@Generated
public class Rating
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "moodys_rating")
    private String moodysRating;

    @Column(name = "sand_p_rating")
    private String sandPRating;

    @Column(name = "fitch_rating")
    private String fitchRating;

    @Column(name = "order_number")
    private Integer orderNumber;

    public Rating()
    {}

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber)
    {}

    public Rating(Integer id, String moodysRating, String sandPRating, String fitchRating, Integer orderNumber)
    {
        this.id = id;
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMoodysRating()
    {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating)
    {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating()
    {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating)
    {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating()
    {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating)
    {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber)
    {
        this.orderNumber = orderNumber;
    }
}
