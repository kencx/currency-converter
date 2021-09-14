package org.rates;

import java.util.HashMap;
import java.time.LocalDate;

public class Currencies {

    private HashMap<String, Double> currencyMap;
    private LocalDate dateUpdated;

    public Currencies() {}

    public Currencies(HashMap<String, Double> currencyMap, LocalDate dateUpdated) {
        this.currencyMap = currencyMap;
        this.dateUpdated = dateUpdated;
    }

    public HashMap<String, Double> getCurrencyMap() {
        return currencyMap;
    }

    public void setCurrencyMap(HashMap<String, Double> currencyMap) {
        this.currencyMap = currencyMap;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
