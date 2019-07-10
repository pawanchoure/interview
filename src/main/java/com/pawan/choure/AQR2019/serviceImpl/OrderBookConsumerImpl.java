package com.pawan.choure.AQR2019.serviceImpl;

import com.pawan.choure.AQR2019.model.AskPrice;
import com.pawan.choure.AQR2019.model.BidPrice;
import com.pawan.choure.AQR2019.model.CentralBook;
import com.pawan.choure.AQR2019.model.OrderBook;
import com.pawan.choure.AQR2019.service.OrderBookConsumer;
import com.pawan.choure.AQR2019.utility.BUYSELL;
import com.pawan.choure.AQR2019.utility.BookType;
import com.pawan.choure.AQR2019.utility.OrderType;
import com.pawan.choure.AQR2019.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.pawan.choure.AQR2019.utility.Utility.centralBookMap;


public class OrderBookConsumerImpl implements Runnable, OrderBookConsumer {
    private static final Logger LOG = LogManager.getLogger(OrderBookConsumerImpl.class);
    protected BlockingQueue<OrderBook> sharedQueue;
    private OrderBook orderBook;
    public AtomicInteger numberOfConsumedMessagesOrderBook
            = new AtomicInteger();

    public OrderBookConsumerImpl(BlockingQueue<OrderBook> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }


    /**
     * transformTradeBook : Transform the TopBook or OrderBook to Central Book which provide
     *
     * @param orderBook
     */
    @Override
    public CentralBook transformTradeBook(OrderBook orderBook) {
        LOG.debug("OrderBookConsumerImpl : transformTradeBook  Started for Ticker " + orderBook.getSymbol());
        if (centralBookMap.containsKey(orderBook.getSymbol())) {
            CentralBook existingObject = centralBookMap.get(orderBook.getSymbol());
            // Code Flow if its NEW ORDER
            if (OrderType.NEW_ORDER.equals(orderBook.getOrderType())) {
                if (orderBook.getBuySell() == BUYSELL.BUY) {
                    existingObject.addBidPrice(orderBook.getLimitPrice(), orderBook.getQuantity(), BookType.ORDER_BOOK, orderBook.getOrderType(), orderBook.getOrderId(), orderBook.getExchange());
                } else if (orderBook.getBuySell() == BUYSELL.SELL) {
                    existingObject.addAskPrice(orderBook.getLimitPrice(), orderBook.getQuantity(), BookType.ORDER_BOOK, orderBook.getOrderType(), orderBook.getOrderId(), orderBook.getExchange());
                }
            } else if (OrderType.MODIFY_ORDER.equals(orderBook.getOrderType()) || OrderType.CANCEL_ORDER.equals(orderBook.getOrderType())) { //CODE FLOW FOR MODIFYING OR CANCELLATION

                //For ASK PRICE
                AskPrice askPrice = existingObject.getAskPriceById(orderBook.getOrderId());
                if (askPrice != null) {
                    if (OrderType.MODIFY_ORDER.equals(orderBook.getOrderType())) {
                        askPrice.setBestAskQuantity(orderBook.getQuantity());
                        askPrice.setOrderType(OrderType.MODIFY_ORDER);
                    } else if (OrderType.CANCEL_ORDER.equals(orderBook.getOrderType())) {
                        askPrice.setOrderType(OrderType.CANCEL_ORDER);

                    }
                }

                //FOR BID PRICE
                BidPrice bidPrice = existingObject.getBidPriceById(orderBook.getOrderId());
                if (bidPrice != null) {
                    if (OrderType.MODIFY_ORDER.equals(orderBook.getOrderType())) {
                        bidPrice.setBestBidQuantity(orderBook.getQuantity());
                    } else if (OrderType.CANCEL_ORDER.equals(orderBook.getOrderType())) {
                        bidPrice.setOrderType(OrderType.CANCEL_ORDER);
                    }
                }


            }

        } else {
            if (orderBook.getBuySell() == BUYSELL.BUY) {
                centralBookMap.put(orderBook.getSymbol(), new CentralBook(orderBook.getSymbol(), orderBook.getExchange(), Utility.getUniqueID(), orderBook.getOrderId(), BookType.ORDER_BOOK, orderBook.getOrderType(), orderBook.getLimitPrice(), orderBook.getQuantity(), null, null));
            } else if (orderBook.getBuySell() == BUYSELL.SELL) {
                centralBookMap.put(orderBook.getSymbol(), new CentralBook(orderBook.getSymbol(), orderBook.getExchange(), Utility.getUniqueID(), orderBook.getOrderId(), BookType.ORDER_BOOK, orderBook.getOrderType(), null, null, orderBook.getLimitPrice(), orderBook.getQuantity()));
            }

        }

        LOG.debug("OrderBookConsumerImpl : transformTradeBook  Completed for Ticker" + orderBook.getSymbol());
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
        LOG.debug("OrderBookConsumerImpl : call  Started");
        while (true) {
            try {
                orderBook = sharedQueue.take();
                numberOfConsumedMessagesOrderBook.incrementAndGet();
                LOG.debug("OrderBookConsumerImpl : run" + orderBook.getSymbol() + "for orderID " + orderBook.getLimitPrice());
                transformTradeBook(orderBook);
                LOG.debug(centralBookMap.toString());
                LOG.debug("OrderBookConsumerImpl : call  Completed");

            } catch (InterruptedException ex) {
                LOG.debug(ex.getMessage());
            }
        }
    }
}
