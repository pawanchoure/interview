package com.pawan.choure.AQR2019;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import com.pawan.choure.AQR2019.model.OrderBook;
import com.pawan.choure.AQR2019.model.TopBook;
import com.pawan.choure.AQR2019.serviceImpl.*;
import com.pawan.choure.AQR2019.utility.BUYSELL;
import com.pawan.choure.AQR2019.utility.OrderType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.*;

/**
 * Unit test for simple App.
 */
public class TradeProcessorTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void whenUsing6TopProducerandOneConsumer()
            throws InterruptedException {
        // given
        int topBookProducer = 6;
        TransferQueue<TopBook> transferQueueTopBook = new LinkedTransferQueue<>();
        ExecutorService executorServiceTop = Executors.newFixedThreadPool(topBookProducer);
        TopBookProducerImpl topBookProducer1 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("IBM", "NASDAQ", new BigDecimal(89.02), 300, new BigDecimal(88.08), 301));
        TopBookProducerImpl topBookProducer2 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("IBM", "NYSE", new BigDecimal(90.02), 200, new BigDecimal(87.08), 201));
        TopBookProducerImpl topBookProducer3 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("IBM", "LSE", new BigDecimal(88.02), 100, new BigDecimal(88.08), 101));

        TopBookProducerImpl topBookProducer4 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("APPL", "NASDAQ", new BigDecimal(500), 300, new BigDecimal(300), 301));
        TopBookProducerImpl topBookProducer5 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("APPL", "NYSE", new BigDecimal(600), 200, new BigDecimal(500), 201));
        TopBookProducerImpl topBookProducer6 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("APPL", "LSE", new BigDecimal(400), 100, new BigDecimal(200), 101));

        // when
        executorServiceTop.execute(topBookProducer1);
        executorServiceTop.execute(topBookProducer2);
        executorServiceTop.execute(topBookProducer3);

        executorServiceTop.execute(topBookProducer4);
        executorServiceTop.execute(topBookProducer5);
        executorServiceTop.execute(topBookProducer6);

        TopBookConsumerImpl topBookConsumer= new TopBookConsumerImpl(transferQueueTopBook);

        executorServiceTop.execute(topBookConsumer);
        // then
        executorServiceTop.awaitTermination(5000, TimeUnit.MILLISECONDS);
        executorServiceTop.shutdown();

        assertEquals(topBookProducer1.numberOfProducedMessagesByTopBook.intValue(), 1);
        assertEquals(topBookProducer2.numberOfProducedMessagesByTopBook.intValue(), 1);
        assertEquals(topBookProducer3.numberOfProducedMessagesByTopBook.intValue(), 1);
        assertEquals(topBookProducer4.numberOfProducedMessagesByTopBook.intValue(), 1);
        assertEquals(topBookProducer5.numberOfProducedMessagesByTopBook.intValue(), 1);
        assertEquals(topBookProducer6.numberOfProducedMessagesByTopBook.intValue(), 1);
        assertEquals(topBookConsumer.numberOfConsumedMessagesTopBook.intValue(), 6);

    }

    @Test
    public void whenUsingOrderProducerandOneConsumer()
            throws InterruptedException {
        // given
        int orderBookProducer = 2;
        TransferQueue<OrderBook> transferQueueOrderBook = new LinkedTransferQueue<>();
        ExecutorService executorServiceOrder = Executors.newFixedThreadPool(orderBookProducer);
        OrderBookProducerImpl orderBookProducer1 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("IBM", "NASDAQ", 701, OrderType.NEW_ORDER, new BigDecimal(96), BUYSELL.SELL, 400));
        OrderBookProducerImpl orderBookProducer2 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("IBM", "NASDAQ", 702, OrderType.NEW_ORDER, new BigDecimal(100), BUYSELL.BUY, 500));
        OrderBookProducerImpl orderBookProducer3 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("IBM", "NYSE", 703, OrderType.NEW_ORDER, new BigDecimal(98), BUYSELL.SELL, 450));
        OrderBookProducerImpl orderBookProducer4 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("IBM", "NYSE", 704, OrderType.NEW_ORDER, new BigDecimal(102), BUYSELL.BUY, 550));
        OrderBookProducerImpl orderBookProducer5 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("IBM", "LSE", 705, OrderType.NEW_ORDER, new BigDecimal(92), BUYSELL.SELL, 350));
        OrderBookProducerImpl orderBookProducer6 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("IBM", "LSE", 706, OrderType.NEW_ORDER, new BigDecimal(99), BUYSELL.BUY, 444));

        OrderBookProducerImpl orderBookProducer7 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("IBM", "NYSE", 704, OrderType.MODIFY_ORDER, BUYSELL.BUY, 700));


        OrderBookProducerImpl orderBookProducer8 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("APPL", "NASDAQ", 801, OrderType.NEW_ORDER, new BigDecimal(96), BUYSELL.SELL, 400));
        OrderBookProducerImpl orderBookProducer9 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("APPL", "NASDAQ", 802, OrderType.NEW_ORDER, new BigDecimal(100), BUYSELL.BUY, 500));
        OrderBookProducerImpl orderBookProducer10 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("APPL", "NYSE", 803, OrderType.NEW_ORDER, new BigDecimal(98), BUYSELL.SELL, 450));
        OrderBookProducerImpl orderBookProducer11 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("APPL", "NYSE", 804, OrderType.NEW_ORDER, new BigDecimal(102), BUYSELL.BUY, 550));
        OrderBookProducerImpl orderBookProducer12 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("APPL", "LSE", 805, OrderType.NEW_ORDER, new BigDecimal(96), BUYSELL.SELL, 350));
        OrderBookProducerImpl orderBookProducer13 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("APPL", "LSE", 806, OrderType.NEW_ORDER, new BigDecimal(99), BUYSELL.BUY, 444));

        OrderBookProducerImpl orderBookProducer14 = new OrderBookProducerImpl(transferQueueOrderBook, new OrderBook("APPL", "NYSE", 804, OrderType.CANCEL_ORDER, BUYSELL.BUY));
        // when
        executorServiceOrder.execute(orderBookProducer1);
        executorServiceOrder.execute(orderBookProducer2);
        executorServiceOrder.execute(orderBookProducer3);
        executorServiceOrder.execute(orderBookProducer4);
        executorServiceOrder.execute(orderBookProducer5);
        executorServiceOrder.execute(orderBookProducer6);
        executorServiceOrder.execute(orderBookProducer7);
        executorServiceOrder.execute(orderBookProducer8);
        executorServiceOrder.execute(orderBookProducer9);
        executorServiceOrder.execute(orderBookProducer10);
        executorServiceOrder.execute(orderBookProducer11);
        executorServiceOrder.execute(orderBookProducer12);
        executorServiceOrder.execute(orderBookProducer13);
        executorServiceOrder.execute(orderBookProducer14);

        OrderBookConsumerImpl orderBookConsumer= new OrderBookConsumerImpl(transferQueueOrderBook);

        executorServiceOrder.execute(orderBookConsumer);
        // then
        executorServiceOrder.awaitTermination(5000, TimeUnit.MILLISECONDS);
        executorServiceOrder.shutdown();

        assertEquals(orderBookProducer1.numberOfProducedMessagesByOrderBook.intValue(), 1);
        assertEquals(orderBookConsumer.numberOfConsumedMessagesOrderBook.intValue(), 14);

    }

}
