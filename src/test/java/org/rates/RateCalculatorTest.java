package org.rates;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class RateCalculatorTest
{
    private RateCalculator calculator;

    @Before
    public void init() {
        HashMap<String, Double> testMap = new HashMap<>();
        testMap.put("HKD", 9.2117);
        testMap.put("SGD", 1.6029);
        testMap.put("USD", 1.1858);

        Currencies testCurrencies = new Currencies();
        testCurrencies.setCurrencyMap(testMap);
        calculator = new RateCalculator(testCurrencies);
    }

    @Test
    public void returnCorrectResultFromConvert()
    {
        double amount = 100;
        double expected = (amount/1.6029)*9.2117;
        double converted = calculator.convert("SGD", "HKD", amount);
        assertEquals(expected, converted, 0.01);
    }

    @Test
    public void returnCorrectRateFromMap()
    {
        double expected = 9.2117;
        double rate = calculator.getRateFromMap("HKD");
        assertEquals(expected, rate, 0.01);
    }

    @Test
    public void returnListOfCurrenciesFromMap()
    {
        List<String> expectedList = new ArrayList<>(Arrays.asList("HKD", "SGD", "USD"));
        List<String> listOfCurrencies = calculator.getAllCurrencies();
        assertEquals(expectedList, listOfCurrencies);
    }
}
