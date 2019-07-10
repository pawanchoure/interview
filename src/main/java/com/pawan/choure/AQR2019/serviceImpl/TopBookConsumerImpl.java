package com.pawan.choure.AQR2019.serviceImpl;

import com.pawan.choure.AQR2019.model.CentralBook;
import com.pawan.choure.AQR2019.model.TopBook;
import com.pawan.choure.AQR2019.service.TopBookConsumer;
import com.pawan.choure.AQR2019.utility.BookType;
import com.pawan.choure.AQR2019.utility.OrderType;
import com.pawan.choure.AQR2019.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.pawan.choure.AQR2019.utility.Utility.centralBookMap;

public class TopBookConsumerImpl implements Runnable, TopBookConsumer {
    private static final Logger LOG = LogManager.getLogger(TopBookConsumerImpl.class);
    protected TransferQueue<TopBook> sharedQueue;
    private TopBook topBook;
    public AtomicInteger numberOfConsumedMessagesTopBook
            = new AtomicInteger();

    public TopBookConsumerImpl(TransferQueue<TopBook> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }


    /**
     * tranformTradetoBook : Transform the TopBook or TopBook to Central Book which provide
     *
     * @param topBook
     */
    @Override
    public CentralBook transformTradeBook(TopBook topBook) {
        LOG.debug("TopBookConsumerImpl : transformTradeBook  Started for Ticker " + topBook.getSymbol());
        if (centralBookMap.containsKey(topBook.getSymbol())) {
            CentralBook existingObject = centralBookMap.get(topBook.getSymbol());
            existingObject.addBidPrice(topBook.getBestBidPrice(), topBook.getBestBidSize(), BookType.TOP_BOOK, OrderType.NEW_ORDER, topBook.getTopBookId(), topBook.getExchange());
            existingObject.addAskPrice(topBook.getBestOfferPrice(), topBook.getBestOfferSize(), BookType.TOP_BOOK, OrderType.NEW_ORDER, topBook.getTopBookId(), topBook.getExchange());

        } else {
            centralBookMap.put(topBook.getSymbol(), new CentralBook(topBook.getSymbol(), topBook.getExchange(), Utility.getUniqueID(), Utility.getUniqueID(), BookType.TOP_BOOK, OrderType.NEW_ORDER, topBook.getBestBidPrice(), topBook.getBestBidSize(), topBook.getBestOfferPrice(), topBook.getBestOfferSize()));

        }
        LOG.debug("TopBookConsumerImpl : transformTradeBook  Completed for Ticker" + topBook.getSymbol());
        return new CentralBook();
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        LOG.debug("TopBookConsumerImpl : call  Started");
        while (true) {
            try {
                topBook = sharedQueue.take();
                numberOfConsumedMessagesTopBook.incrementAndGet();
                LOG.debug("TopBookConsumerImpl : run" + topBook.getSymbol());
                transformTradeBook(topBook);
                LOG.debug(centralBookMap.toString());
                LOG.debug("TopBookConsumerImpl : call  Completed");
            } catch (InterruptedException ex) {

            }
        }
    }
}
