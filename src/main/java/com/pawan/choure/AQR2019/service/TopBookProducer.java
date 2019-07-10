package com.pawan.choure.AQR2019.service;

import com.pawan.choure.AQR2019.model.TopBook;

public interface TopBookProducer {

    /**
     * publishTrade : Method to publish Message
     */
    void publishTrade(TopBook tradeBook);
}
