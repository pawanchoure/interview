package com.pawan.choure.AQR2019.service;

import com.pawan.choure.AQR2019.model.CentralBook;
import com.pawan.choure.AQR2019.model.OrderBook;

public interface OrderBookConsumer {


    /**
     * transformTradetoBook : Transform the TopBook or OrderBook to Central Book which provide
     */
    CentralBook transformTradeBook(OrderBook tradeBookList);


}
