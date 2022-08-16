package com.mytech;

import com.mytech.bean.Utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Currency;

public class CurrencyConverter {

    public static void main(String[] args) {
//        String[] country_code={"AUD","CAD","CNY","CZK","DKK","EUR","GBP","JPY", "NOK","NZD","USD"};

        System.out.println(convertCurrency(args));
    }
    public static String convertCurrency(String[] args){
        StringBuilder output = new StringBuilder("No Input provided., Provide input eg : AUD 100.00 in USD");
        if(args.length>=4){
            try{
                String base = args[0].toUpperCase();
                String amountString = args[1];
                Double amount = Double.parseDouble(amountString);
                String term = args[3].toUpperCase();
                output.setLength(0);
                output.append(Utility.converter(base,term,amount));
            }catch (NullPointerException nullPntrExce){
                output.setLength(0);
                output.append("Input values are mismatch, Provide input as eg : AUD 100.00 in USD");
            }catch (NumberFormatException numbrFrmtExc){
                output.setLength(0);
                output.append("Provide correct amount to convert. eg : AUD 100.00 in USD");
            }catch (Exception exc){
                output.setLength(0);
                output.append("Provide input as eg : AUD 100.00 in USD");
            }
        }
        return output.toString();
    }

}
