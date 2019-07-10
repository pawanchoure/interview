package com.pawan.choure.AQR2019;

import com.pawan.choure.AQR2019.model.OrderBook;
import com.pawan.choure.AQR2019.model.TopBook;
import com.pawan.choure.AQR2019.serviceImpl.*;
import com.pawan.choure.AQR2019.utility.BUYSELL;
import com.pawan.choure.AQR2019.utility.OrderType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;


public class TradeProcessorApp {
    private static final Logger LOG = LogManager.getLogger(TradeProcessorApp.class);


    public static void main(String[] args) {
        int topBookProducer = 2;
        int orderBookProducer = 2;
        int topBookConsumer = 2;
        int orderBookConsumer = 2;

        TransferQueue<TopBook> transferQueueTopBook = new LinkedTransferQueue<>();
        TransferQueue<OrderBook> transferQueueOrderBook = new LinkedTransferQueue<>();

        ExecutorService executorServiceTop = Executors.newFixedThreadPool(topBookProducer);
        ExecutorService executorServiceOrder = Executors.newFixedThreadPool(orderBookProducer);
        TopBookProducerImpl topBookProducer1 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("IBM", "NASDAQ", new BigDecimal(89.02), 300, new BigDecimal(88.08), 301));
        TopBookProducerImpl topBookProducer2 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("IBM", "NYSE", new BigDecimal(90.02), 200, new BigDecimal(87.08), 201));
        TopBookProducerImpl topBookProducer3 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("IBM", "LSE", new BigDecimal(88.02), 100, new BigDecimal(88.08), 101));

        TopBookProducerImpl topBookProducer4 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("APPL", "NASDAQ", new BigDecimal(500), 300, new BigDecimal(300), 301));
        TopBookProducerImpl topBookProducer5 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("APPL", "NYSE", new BigDecimal(600), 200, new BigDecimal(500), 201));
        TopBookProducerImpl topBookProducer6 = new TopBookProducerImpl(transferQueueTopBook, new TopBook("APPL", "LSE", new BigDecimal(400), 100, new BigDecimal(200), 101));

        executorServiceTop.execute(topBookProducer1);
        executorServiceTop.execute(topBookProducer2);
        executorServiceTop.execute(topBookProducer3);

        executorServiceTop.execute(topBookProducer4);
        executorServiceTop.execute(topBookProducer5);
        executorServiceTop.execute(topBookProducer6);

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

        executorServiceTop.execute(new TopBookConsumerImpl(transferQueueTopBook));
        executorServiceOrder.execute(new OrderBookConsumerImpl(transferQueueOrderBook));


/*        System.out.println(centralBookMap.toString());

        CentralBookServiceImpl centralBookService= new CentralBookServiceImpl();

       List<String> result= centralBookService.getTop5PriceBySymbol("APPL");
        System.out.println(result.toString());*/



        // Let the simulation run for, say, 10 seconds
        try {
            Thread.sleep(10 * 1000);
            executorServiceOrder.shutdown();
            executorServiceTop.shutdown();
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
