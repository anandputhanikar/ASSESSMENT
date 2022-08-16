package com.mytech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    @Test
    void convertCurrencyTest() {
        String[] args = {};
        String result = CurrencyConverter.convertCurrency(args);
        assertEquals("No Input provided., Provide input eg : AUD 100.00 in USD",result);
    }

    @Test
    void convertCurrencyNullTest() {
        String[] args = {null,"100","in",null};
        String result = CurrencyConverter.convertCurrency(args);
        assertEquals("Input values are mismatch, Provide input as eg : AUD 100.00 in USD",result);
    }

    @Test
    void convertCurrencyNumberFormatTest() {
        String[] args = {"USD","AUD","IN",null};
        String result = CurrencyConverter.convertCurrency(args);
        assertEquals("Provide correct amount to convert. eg : AUD 100.00 in USD",result);
    }
    @Test
    void convertCurrencyAUDDKKTest() {
        String[] args = {"AUD","100.00","in","DKK"};
        String result = CurrencyConverter.convertCurrency(args);
        assertEquals("AUD 100.00 = DKK 505.76",result);
    }
    @Test
    void convertCurrencyAUDTest() {
        String[] args = {"AUD","in","100","DKK"};
        String result = CurrencyConverter.convertCurrency(args);
        assertEquals("Provide correct amount to convert. eg : AUD 100.00 in USD",result);
    }

    @Test
    void convertCurrencyAUDAUDTest() {
        String[] args = {"AUD","101.00","in","AUD"};
        String result = CurrencyConverter.convertCurrency(args);
        assertEquals("AUD 101.00 = AUD 101.00",result);
    }

    @Test
    void convertCurrencyAUDUSDTest() {
        String[] args = {"AUD","100.00","in","USD"};
        String result = CurrencyConverter.convertCurrency(args);
        assertEquals("AUD 100.00 = USD 83.71", result);
    }
}