package com.pawan.choure.AQR2019.service;

import com.pawan.choure.AQR2019.model.CentralBook;
import com.pawan.choure.AQR2019.model.TopBook;

public interface TopBookConsumer {

    /**
     * tranformTradetoBook : Transform the TopBook or OrderBook to Central Book which provide
     */
    CentralBook transformTradeBook(TopBook tradeBookList);


}
