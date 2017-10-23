package com.cs.validator;

import java.util.Currency;
import java.util.Set;

public class IsoCurrencyUtil {
	    private static final Set<Currency> ISO_CURRENCIES = 
	        Currency.getAvailableCurrencies();
	   	    
	    private IsoCurrencyUtil() {}

	    public static boolean isValidISOCurrency(String code) {
	        return ISO_CURRENCIES.stream()
	        		             .filter(currency -> currency.getCurrencyCode().equals(code))
	        		             .findFirst()
	        		             .isPresent();
	    }
}
