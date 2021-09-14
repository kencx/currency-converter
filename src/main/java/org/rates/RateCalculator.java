package org.rates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RateCalculator {

    private Currencies currencies;

    public RateCalculator() {}

    public RateCalculator(Currencies currencies) {
        this.currencies = currencies;
    }


    /**
     * Key method to convert currency
     * @param from Currency to convert from
     * @param to Currency to convert to
     * @param amount Amount to convert
     * @return Converted amount
     */
    public double convert(String from, String to, double amount) {

        double fromRate = getRateFromMap(from);
        double toRate = getRateFromMap(to);

        return (amount / fromRate) * toRate;
    }


    /**
     * Finds the rate of given currency
     * @param currency Currency of interest
     * @return Associated rate of given currency
     */
    public double getRateFromMap(String currency) {

        HashMap<String, Double> map = currencies.getCurrencyMap();
        return map.get(currency);
    }


    /**
     * Provides a list of currency names without their rates
     * @return List of currency names without their rates
     */
    public List<String> getAllCurrencies() {

        HashMap<String, Double> map = currencies.getCurrencyMap();
        List<String> listOfCurrencies = new ArrayList<>(map.keySet());
        Collections.sort(listOfCurrencies);

        return listOfCurrencies;
    }
}
