package com.pawan.choure.AQR2019.model;

import com.pawan.choure.AQR2019.utility.AskPriceComparator;
import com.pawan.choure.AQR2019.utility.BidPriceComparator;
import com.pawan.choure.AQR2019.utility.BookType;
import com.pawan.choure.AQR2019.utility.OrderType;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * CentralBook : This class hold the enriched Object which has symbol and Set of Bid and Ask Prices associated with a Symbol
 */
public class CentralBook  {
    String symbol;
    Integer centralBookId;

    TreeSet<BidPrice> bidPriceTreeSet;
    TreeSet<AskPrice> askPriceTreeSet;


    public CentralBook(String symbol, String exchange, Integer centralBookId, Integer id, BookType bookType, OrderType orderType, BigDecimal bestBidPrice, Integer bestBidQuantity, BigDecimal bestOfferPrice, Integer bestOfferQuantity) {
        this.symbol=symbol;
        this.centralBookId = centralBookId;
        this.bidPriceTreeSet = new TreeSet<>(new BidPriceComparator());
        if(bestBidPrice!=null){
            this.bidPriceTreeSet.add(new BidPrice(bestBidPrice, bestBidQuantity, bookType,orderType,id,exchange));
        }
        this.askPriceTreeSet = new TreeSet<>(new AskPriceComparator());
        if(bestOfferPrice!=null){
            this.askPriceTreeSet.add(new AskPrice(bestOfferPrice, bestOfferQuantity, bookType,orderType,id,exchange));
        }


    }

    public CentralBook() {

    }


    public Integer getCentralBookId() {
        return centralBookId;
    }

    public void setCentralBookId(Integer centralBookId) {
        this.centralBookId = centralBookId;
    }

    public TreeSet<BidPrice> getBidPriceTreeSet() {
        return bidPriceTreeSet;
    }

    public void setBidPriceTreeSet(TreeSet<BidPrice> bidPriceTreeSet) {
        this.bidPriceTreeSet = bidPriceTreeSet;
    }

    public TreeSet<AskPrice> getAskPriceTreeSet() {
        return askPriceTreeSet;
    }

    public void setAskPriceTreeSet(TreeSet<AskPrice> askPriceTreeSet) {
        this.askPriceTreeSet = askPriceTreeSet;
    }

    public void addBidPrice(BigDecimal bidPrice, Integer bidQuantity, BookType bookType, OrderType orderType, Integer id,String exchange){
        this.bidPriceTreeSet.add(new BidPrice(bidPrice,bidQuantity,bookType,orderType,id,exchange));
    }

    public void addBidPrice(BidPrice bidPrice){
        this.bidPriceTreeSet.add(bidPrice);
    }


    public void addAskPrice(BigDecimal askPrice, Integer askQuantity, BookType bookType, OrderType orderType, Integer id,String exchange){
        this.askPriceTreeSet.add(new AskPrice(askPrice,askQuantity,bookType, orderType, id,exchange));
    }
    public void addAskPrice(AskPrice askPrice){
        this.askPriceTreeSet.add(askPrice);
    }

    public AskPrice getAskPriceById(Integer id){
        Iterator<AskPrice> askPriceIterator=this.getAskPriceTreeSet().iterator();
        while(askPriceIterator.hasNext()) {
            AskPrice askPrice = askPriceIterator.next();
            if(id.equals(askPrice.getId() ))
                return askPrice;
        }
        return null;
    }

    public BidPrice getBidPriceById(Integer id){
        Iterator<BidPrice> bidPriceIterator=this.getBidPriceTreeSet().iterator();
        while(bidPriceIterator.hasNext()) {
            BidPrice bidPrice = bidPriceIterator.next();
            if(id.equals(bidPrice.getId()))
                return bidPrice;
        }
        return null;
    }

    @Override
    public String toString() {
        return "CentralBook{" +
                "centralBookId=" + centralBookId +
                ", bidPriceTreeSet=" + bidPriceTreeSet +
                ", askPriceTreeSet=" + askPriceTreeSet +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
