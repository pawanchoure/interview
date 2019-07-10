package com.pawan.choure.AQR2019.serviceImpl;

import com.pawan.choure.AQR2019.model.TopBook;
import com.pawan.choure.AQR2019.service.TopBookProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class TopBookProducerImpl implements Runnable, TopBookProducer {
    private static final Logger LOG = LogManager.getLogger(TopBookProducerImpl.class);
    protected TransferQueue<TopBook> topSharedQueue;
    private TopBook topBook;
    public AtomicInteger numberOfProducedMessagesByTopBook
            = new AtomicInteger();

    /**
     * TopBookProducerImpl : Constructor for creating TopBookProducerImpl
     *
     * @param queue
     * @param topBook
     */
    public TopBookProducerImpl(TransferQueue<TopBook> queue, TopBook topBook) {
        this.topSharedQueue = queue;
        this.topBook = topBook;
    }

    /**
     * publishTrade : Method to publish Message
     *
     * @param topBook
     */
    @Override
    public void publishTrade(TopBook topBook) {
        try {
            // Put the OrderBook into Shared Queue
            topSharedQueue.put(topBook);
            numberOfProducedMessagesByTopBook.incrementAndGet();
            LOG.info("TopBookProducerImpl:publishTrade Produced - Queue Size is now = " + topSharedQueue.size());

        } catch (InterruptedException e) {
            LOG.debug("TopBookProducerImpl:publishTrade Issue with publish Trade " + e.getMessage());
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
        LOG.debug("TopBookProducerImpl:run Starting Publishing Trade ");
        publishTrade(topBook);
        LOG.debug("TradeBookProducer:run Finished Publishing Trade ");
    }
}
