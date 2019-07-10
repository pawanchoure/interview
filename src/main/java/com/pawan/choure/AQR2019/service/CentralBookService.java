package com.pawan.choure.AQR2019.service;

import com.pawan.choure.AQR2019.model.CentralBook;

import java.util.List;
import java.util.Map;

/**
 * CentralBookService: Interface to have Central Book Service
 */
public interface CentralBookService {

    /**
     * getTop5PriceBySymbol : Get Top 5 Prices by Symbol
     * @param symbol
     * List<String>
     */
    List<String> getTop5PriceBySymbol(String symbol);



}
