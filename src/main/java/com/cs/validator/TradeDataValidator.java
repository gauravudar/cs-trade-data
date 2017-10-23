package com.cs.validator;

import java.util.List;

import com.cs.data.TradeDataWrapper;
import com.cs.viewobject.DataValidationViewObject;

public interface TradeDataValidator {
    String VALUE_DATE_EMPTY_MSG = "Value date can't be empty for spot or forward type";
    String VALUE_DATE_IN_PAST_MSG = "Value date can't before than trade date";
    String VALUE_DATE_WEEKEND_NON_WORKING_DAY_MSG = "Value date can't fall on weekend or non working day for currency";
    String TRADE_DATE_EMPTY_MSG = "Trade date can't be empty";
    String CUSTOMER_NOT_SUPPORTED = "The given customer is not allowed to trade";
    String CURRENCY_NOT_VALID = "Given currency is not valid as per ISO 4217 Code";
    String OPTION_STYLE_NOT_VALID ="Option style can be either American or European";
    String AMERICAN_STYLE_EXERCISE_START_DATE_EMPTY_MSG="Exercise start date can't be empty for american option style ";
    String AMERICAN_STYLE_EXERCISE_START_DATE_NOT_VALID="American option style exercise start date should be after trade date and before expiry date";
    String OPTION_EXPIRY_PREMIUM_DATE_NOT_VALID = "Option expiry date and premium date shall be before delivery date";

    DataValidationViewObject validate(List<TradeDataWrapper> tradeDataObjects);
}
