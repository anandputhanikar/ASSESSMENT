package com.mytech;

import java.text.DecimalFormat;
import java.util.*;

public class Utility {
    public static final Map<String,Double> currencyPair = new HashMap<>();
    public static String[] countryCode={"AUD","CAD","CNY","CZK","DKK","EUR","GBP","JPY", "NOK","NZD","USD"};

   static double updatedRate=1;
    private static void currencyMap(){
        currencyPair.put("AUDUSD",0.8371);
        currencyPair.put("USDCNY",6.1715);
        currencyPair.put("EURUSD",1.2315);
        currencyPair.put("GBPUSD",1.5683);
        currencyPair.put("NZDUSD",0.7750);
        currencyPair.put("USDJPY",119.95);
        currencyPair.put("EURCZK",27.6028);
        currencyPair.put("EURDKK",7.4405);
        currencyPair.put("EURNOK",8.6651);

    }

    private static double getCurrencyRate(String to, String from)throws Exception{
        currencyMap();
        double crossCurrencyRate =1;
        double exchangeRate=1;
        double convertedAmount=0;
        StringBuilder currencyName = new StringBuilder();

        if(currencyPair.containsKey(to.concat(from))){
            currencyName.append(to.concat(from));
            exchangeRate = currencyPair.get(currencyName.toString());

        }else if(currencyPair.containsKey(from.concat(to))){
            currencyName.append(from.concat(to));
            exchangeRate = 1/currencyPair.get(currencyName.toString());
        }else{
            String term = getCurrencyCode(to,from);
            exchangeRate = getCurrencyRate(to,term) * getCurrencyRate(term,from);
        }

        return exchangeRate;
    }
    public static double crossConverter(String base, String term,double amount)throws Exception{
        double exchangeRate =1;
        double convertedAmount =0;

        if(!base.equals(term)){
            exchangeRate = getCurrencyRate(base,term);
        }
        if(exchangeRate>=0){
            convertedAmount = exchangeRate * amount;
        }
        return convertedAmount;
    }

    public static String converter(String base, String term,double amount)throws Exception{
        StringBuilder result = new StringBuilder();
        if(Arrays.asList(countryCode).contains(base) && Arrays.asList(countryCode).contains(term)) {
            double convertedAmount = crossConverter(base, term, amount);
            result.append(convertedResult(base, term, amount, convertedAmount));
        }
        else
        {
            result.append("Unable to find rate for ").append(base).append("/").append(term);
        }

       return result.toString();
    }

    private static String convertedResult(String base,String term,double amount,double convertedAmount)throws Exception{
        StringBuilder sb = new StringBuilder();
        Currency currency = Currency.getInstance(base);
        int baseDecimalPlace = currency.getDefaultFractionDigits();
        DecimalFormat baseDecimalFormat = new DecimalFormat();
        baseDecimalFormat.setMinimumFractionDigits(baseDecimalPlace);
        baseDecimalFormat.setMaximumFractionDigits(baseDecimalPlace);
        int termDecimalPlace = Currency.getInstance(term).getDefaultFractionDigits();
        DecimalFormat termDecimalFormat = new DecimalFormat();
        termDecimalFormat.setMinimumFractionDigits(termDecimalPlace);
        termDecimalFormat.setMaximumFractionDigits(termDecimalPlace);

        sb.append(base).append(" ").append(baseDecimalFormat.format(amount)).append(" = ").append(term).append(" ").append(termDecimalFormat.format(convertedAmount));
        return sb.toString();
    }
    private static String getCurrencyCode(String to, String from){
        boolean isCrossCurrency = isCrossCurrency(to.concat(from));

        if(isCrossCurrency){
            return "EUR";
        }else{
            return "USD";
        }

    }

    public static boolean isCrossCurrency(String toFrom){

        List<String> crossCurrencyList = Arrays.asList("CZKDKK","CZKNOK","CZKUSD","DKKCZK","DKKNOK","DKKUSD","EURNOK","EURUSD","NOKCZK","NOKDKK","NOKUSD","USDCZK","USDDKK","USDNOK");

        if(crossCurrencyList.contains(toFrom)){
                return  true;
        }
        return false;
    }


}
