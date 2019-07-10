package com.pawan.choure.AQR2019.serviceImpl;

import com.pawan.choure.AQR2019.model.OrderBook;
import com.pawan.choure.AQR2019.service.OrderBookProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class OrderBookProducerImpl implements Runnable, OrderBookProducer {
    private static final Logger LOG = LogManager.getLogger(OrderBookProducerImpl.class);
    protected TransferQueue<OrderBook> orderSharedQueue;
    private OrderBook orderBook;
    public AtomicInteger numberOfProducedMessagesByOrderBook
            = new AtomicInteger();

    /**
     * OrderBookProducerImpl : Constructor for creating OrderBookProducerImpl
     *
     * @param queue
     * @param orderBook
     */
    public OrderBookProducerImpl(TransferQueue<OrderBook> queue, OrderBook orderBook) {
        this.orderSharedQueue = queue;
        this.orderBook = orderBook;
    }

    /**
     * publishTrade : Method to publish Message
     *
     * @param orderBook
     */
    @Override
    public void publishTrade(OrderBook orderBook) {
        try {
            // Put the OrderBook into Shared Queue
            orderSharedQueue.put(orderBook);
            numberOfProducedMessagesByOrderBook.incrementAndGet();
            LOG.info("OrderBookProducerImpl:publishTrade Produced - Queue Size is now = " + orderSharedQueue.size());

        } catch (InterruptedException e) {
            LOG.debug("OrderBookProducerImpl:publishTrade Issue with publish Trade " + e.getMessage());
            e.printStackTrace();
        }
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
        LOG.debug("OrderBookProducerImpl:run Starting Publishing Trade ");
        publishTrade(orderBook);
        LOG.debug("OrderBookProducerImpl:run Finished Publishing Trade ");
    }
}
