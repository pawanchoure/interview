package com.pawan.choure.AQR2019.service;

import com.pawan.choure.AQR2019.model.OrderBook;

public interface OrderBookProducer {

    /**
     * publishTrade : Method to publish Message
     */
    void publishTrade(OrderBook tradeBook);
}
