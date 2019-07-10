package com.pawan.choure.AQR2019.serviceImpl;

import com.pawan.choure.AQR2019.model.AskPrice;
import com.pawan.choure.AQR2019.model.BidPrice;
import com.pawan.choure.AQR2019.model.CentralBook;
import com.pawan.choure.AQR2019.service.CentralBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static com.pawan.choure.AQR2019.utility.Utility.centralBookMap;

public class CentralBookServiceImpl implements CentralBookService{
    private static final Logger LOG = LogManager.getLogger(CentralBookServiceImpl.class);

    /**
     * getTop5PriceBySymbol : Get Top 5 Prices by Symbol
     *
     * @param symbol
     * @return List<CSVRecord></CSVRecord>
     */
    @Override
    public List<String> getTop5PriceBySymbol(String symbol) {
        LOG.info("CentralBookServiceImpl: getTop5PriceBySymbol Started");
        List<String> result = new ArrayList<>();
        //Check if Symbol exists
        if (centralBookMap.containsKey(symbol)) {
            CentralBook centralBook = centralBookMap.get(symbol);

            //Get the Iterator -- Since the result we are storing using Treeset is already sorted based on Price
            Iterator<BidPrice> bidPriceIterator = centralBook.getBidPriceTreeSet().iterator();
            Iterator<AskPrice> askPriceIterator = centralBook.getAskPriceTreeSet().iterator();

            int counter = 0;
            while (bidPriceIterator.hasNext() && askPriceIterator.hasNext() && counter < 5) {
                StringBuilder sb = new StringBuilder();
                BidPrice bidPrice = bidPriceIterator.next();
                AskPrice askPrice = askPriceIterator.next();
                sb.append("Level ").append(counter).append(" :").append(bidPrice.getBestBidPrice());
                //Get Quantity for that Bid Price
                Integer sumOfQuantityBid = centralBook.getBidPriceTreeSet().stream().filter(x -> Objects.nonNull(x) && x.getBestBidPrice().equals(bidPrice.getBestBidPrice())).mapToInt(x -> x.getBestBidQuantity()).sum();
                sb.append(", " + sumOfQuantityBid);

                sb.append(", " + askPrice.getBestAskPrice());
                //Get Quantity for that Ask Price
                Integer sumOfQuantityAsk = centralBook.getAskPriceTreeSet().stream().filter(x -> Objects.nonNull(x) && x.getBestAskPrice().equals(askPrice.getBestAskPrice())).mapToInt(x -> x.getBestAskQuantity()).sum();
                sb.append(", " + sumOfQuantityAsk);
                counter++;
                result.add(sb.toString());
            }
        }
        LOG.info("CentralBookServiceImpl: getTop5PriceBySymbol Completed");
        return result;
    }

}
