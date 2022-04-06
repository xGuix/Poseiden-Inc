package com.nnk.springboot.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "bid_list")
public class BidList
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    @NonNull
    @NotBlank(message = "Account is mandatory")
    @Size(max = 30)
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 30)
    @Column(name = "type")
    private String type;

    @Positive(message = "Bid Quantity must be greater than zero")
    @Column(name = "bid_quantity")
    private Double bidQuantity;

    @Column(name = "ask_quantity")
    private Double askQuantity;

    @Column(name = "bid")
    private Double bid;

    @Column(name = "ask")
    private Double ask;

    @Size(max=125)
    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "bid_list_date")
    private Timestamp bidListDate;

    @Size(max=125)
    @Column(name = "commentary")
    private String commentary;

    @Size(max=125)
    @Column(name = "security")
    private String security;

    @Size(max=10)
    @Column(name = "status")
    private String status;

    @Size(max=125)
    @Column(name = "trader")
    private String trader;

    @Size(max=125)
    @Column(name = "book")
    private String book;

    @Size(max=125)
    @Column(name = "creation_name")
    private String creationName;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Timestamp creationDate;

    @Size(max=125)
    @Column(name = "revision_name")
    private String revisionName;

    @Column(name = "revision_date")
    private Timestamp revisionDate;

    @Size(max=125)
    @Column(name = "deal_name")
    private String dealName;

    @Size(max=125)
    @Column(name = "deal_type")
    private String dealType;

    @Size(max=125)
    @Column(name = "source_list_id")
    private String sourceListId;

    @Size(max=125)
    @Column(name = "side")
    private String side;

    public BidList()
    {}

    public BidList(String account, String type, Double bidQuantity)
    {}

    public BidList( Integer bidListId,
                    @NonNull String account,
                    String type,
                    Double bidQuantity,
                    Double askQuantity,
                    Double bid,
                    Double ask,
                    String benchmark,
                    Timestamp bidListDate,
                    String commentary,
                    String security,
                    String status,
                    String trader,
                    String book,
                    String creationName,
                    Timestamp creationDate,
                    String revisionName,
                    Timestamp revisionDate,
                    String dealName,
                    String dealType,
                    String sourceListId,
                    String side)
    {
        this.bidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
        this.benchmark = benchmark;
        this.bidListDate = bidListDate;
        this.commentary = commentary;
        this.security = security;
        this.status = status;
        this.trader = trader;
        this.book = book;
        this.creationName = creationName;
        this.creationDate = creationDate;
        this.revisionName = revisionName;
        this.revisionDate = revisionDate;
        this.dealName = dealName;
        this.dealType = dealType;
        this.sourceListId = sourceListId;
        this.side = side;
    }

    public Integer getBidListId()
    {
        return bidListId;
    }

    public void setBidListId(Integer bidListId)
    {
        this.bidListId = bidListId;
    }

    @NonNull
    public String getAccount()
    {
        return account;
    }

    public void setAccount(@NonNull String account)
    {
        this.account = account;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Double getBidQuantity()
    {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity)
    {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity()
    {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity)
    {
        this.askQuantity = askQuantity;
    }

    public Double getBid()
    {
        return bid;
    }

    public void setBid(Double bid)
    {
        this.bid = bid;
    }

    public Double getAsk()
    {
        return ask;
    }

    public void setAsk(Double ask)
    {
        this.ask = ask;
    }

    public String getBenchmark()
    {
        return benchmark;
    }

    public void setBenchmark(String benchmark)
    {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate()
    {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate)
    {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary)
    {
        this.commentary = commentary;
    }

    public String getSecurity()
    {
        return security;
    }

    public void setSecurity(String security)
    {
        this.security = security;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTrader()
    {
        return trader;
    }

    public void setTrader(String trader)
    {
        this.trader = trader;
    }

    public String getBook()
    {
        return book;
    }

    public void setBook(String book)
    {
        this.book = book;
    }

    public String getCreationName()
    {
        return creationName;
    }

    public void setCreationName(String creationName)
    {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getRevisionName()
    {
        return revisionName;
    }

    public void setRevisionName(String revisionName)
    {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate()
    {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate)
    {
        this.revisionDate = revisionDate;
    }

    public String getDealName()
    {
        return dealName;
    }

    public void setDealName(String dealName)
    {
        this.dealName = dealName;
    }

    public String getDealType()
    {
        return dealType;
    }

    public void setDealType(String dealType)
    {
        this.dealType = dealType;
    }

    public String getSourceListId()
    {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId)
    {
        this.sourceListId = sourceListId;
    }

    public String getSide()
    {
        return side;
    }

    public void setSide(String side)
    {
        this.side = side;
    }
}