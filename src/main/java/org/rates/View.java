package org.rates;

import org.exceptions.InvalidInputException;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class View {

    public void start() throws InvalidInputException {

        Scanner menu = new Scanner(System.in);
        Currencies currencies = new Currencies();
        Parser parser = new Parser();
        parser.parseDocument(currencies);
        RateCalculator calculator = new RateCalculator(currencies);

        List<String> listOfStrings = calculator.getAllCurrencies();
        int maxIdx = listOfStrings.size();

        // Accept inputs
        System.out.println("Welcome to Currency Converter.");
        System.out.println("Please choose the currency you would like to convert from:");
        printCurrencies(listOfStrings);
        int fromCurrency = acceptInput(menu, maxIdx);

        System.out.println("Please choose the currency you would like to convert to:");
        printCurrencies(listOfStrings);
        int toCurrency = acceptInput(menu, maxIdx);

        if (fromCurrency == toCurrency) {
            throw new InvalidInputException("Both currencies are the same!");
        }

        System.out.println("How much would you like to convert?");
        double amount = menu.nextDouble();

        if (amount <= 0) {
            throw new InvalidInputException("Invalid amount entered!");
        }


        // Perform calculation
        String from = listOfStrings.get(fromCurrency);
        String to = listOfStrings.get(toCurrency);
        double converted = calculator.convert(from, to, amount);
        converted = Math.round(converted*100.0)/100.0;

        // Print output
        String givenAmount = from + amount;
        String newAmount = to + converted;
        System.out.println(givenAmount + " = " + newAmount);
        menu.close();
    }


    public int acceptInput(Scanner menu, int maxNum) throws InvalidInputException {

        int num = menu.nextInt();
        if (num < 0 || num >= maxNum) {

            throw new InvalidInputException("Invalid currency selected");
        }
        return num;
    }


    // Print list of currencies in readable format
    public void printCurrencies(List<String> list) {

        for (int i = 0; i < list.size(); i += 3) { 	// prints 3 columns

            Function<Integer, String> formatDigit = digit -> String.format("%02d", digit);
            System.out.print("[" + formatDigit.apply(i) + "] " + list.get(i) + " ");

            if (i+1 < list.size()) {
                System.out.print("\t" + "[" + formatDigit.apply(i+1) + "] " + list.get(i+1) + " ");

                if (i+2 < list.size()) {
                    System.out.println("\t" + "[" + formatDigit.apply(i+2) + "] " + list.get(i+2));
                }
            }
        }
        System.out.println();
    }

}
