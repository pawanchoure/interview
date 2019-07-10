package com.pawan.choure.AQR2019.model;

import com.pawan.choure.AQR2019.utility.BookType;
import com.pawan.choure.AQR2019.utility.OrderType;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * BidPrice : Bid Price class used to hold the Bid Price Object
 */
public class BidPrice {
    BigDecimal bestBidPrice;
    Integer bestBidQuantity;
    BookType bookType;
    OrderType orderType;
    Integer id;
    String exchange;

    /**
     * BidPrice : Creating New Instance
     * @param bestBidPrice
     * @param bestBidQuantity
     * @param bookType
     * @param orderType
     * @param id
     * @param exchange
     */
    public BidPrice(BigDecimal bestBidPrice, Integer bestBidQuantity,  BookType bookType, OrderType orderType,Integer id,String exchange) {
        this.bestBidPrice=bestBidPrice;
        this.bestBidQuantity=bestBidQuantity;
        this.bookType =bookType;
        this.orderType=orderType;
        this.id=id;
        this.exchange=exchange;
    }

    /**
     * Bid Price : Instance created for Searching the existing Bid Price based on ID
     * @param id
     */
    public BidPrice(Integer id) {
        this.id=id;
    }


    public BigDecimal getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(BigDecimal bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public Integer getBestBidQuantity() {
        return bestBidQuantity;
    }

    public void setBestBidQuantity(Integer bestBidQuantity) {
        this.bestBidQuantity = bestBidQuantity;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "BidPrice{" +
                "bestBidPrice=" + bestBidPrice +
                ", bestBidQuantity=" + bestBidQuantity +
                ", bookType=" + bookType +
                ", orderType=" + orderType +
                ", id=" + id +
                ", exchange='" + exchange + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrice bidPrice = (BidPrice) o;
        return id.equals(bidPrice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
