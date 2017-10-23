package com.cs.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cs.data.ForwardTradeDataObject;
import com.cs.data.OptionTradeDataObject;
import com.cs.data.TradeDataWrapper;
import com.cs.dto.TradeType;
import com.cs.viewobject.DataValidationError;
import com.cs.viewobject.DataValidationViewObject;
import com.cs.dto.OptionExerciseStyle;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static com.cs.utils.DateUtils.getStringDateAsLocalDate;;

@Service
public class TradeDataValidatorImpl implements TradeDataValidator {
	private static final Set<String> COUNTER_PARTIES = new HashSet<>(Arrays.asList("PLUTO1", "PLUTO2"));

    @Override
    public DataValidationViewObject validate(List<TradeDataWrapper> tradeDataObjects) {
    	DataValidationViewObject validationResult = new DataValidationViewObject(new ArrayList<>());
    	tradeDataObjects.stream().forEach(tradeData -> getTradeDataValidated(validationResult,tradeData));
    	return validationResult;
    }

    private void getTradeDataValidated(DataValidationViewObject validationResult, TradeDataWrapper tradeData) {    	  
    	  
    	  if(!COUNTER_PARTIES.contains(tradeData.getTradeDataObject().getCustomer()))
    	  {
    		  validationResult.addError(new DataValidationError(tradeData.toString(), CUSTOMER_NOT_SUPPORTED));
    	  }
    	  
    	  if(!IsoCurrencyUtil.isValidISOCurrency(tradeData.getTradeDataObject().getCcyPair().substring(0, 3))||
    			  !IsoCurrencyUtil.isValidISOCurrency(tradeData.getTradeDataObject().getCcyPair().substring(3, 6)))
    	  {
    		  validationResult.addError(new DataValidationError(tradeData.toString(), CURRENCY_NOT_VALID));
    	  }
    	  
    	  if(tradeData.getTradeDataObject().getType()==TradeType.VanillaOption){
    		  validateOptionData(validationResult, tradeData.getOptionTradeDataObject());
    	  }
    	  
    	  if(tradeData.getTradeDataObject().getType()==TradeType.Forward || tradeData.getTradeDataObject().getType()==TradeType.Spot){
    		  validateForwardData(validationResult, tradeData.getForwardTradeDataObject());
    	  }  	  
          
    }
    
    private void validateOptionData(DataValidationViewObject validationResult, OptionTradeDataObject tradeData){
    	if(tradeData.getStyle()==OptionExerciseStyle.AMERICAN && tradeData.getStyle()==OptionExerciseStyle.EUROPEAN){
    		validationResult.addError(new DataValidationError(tradeData.toString(), OPTION_STYLE_NOT_VALID));
    	}
    	
    	if(tradeData.getStyle()==OptionExerciseStyle.AMERICAN && tradeData.getExcerciseStartDate()==null){
    		validationResult.addError(new DataValidationError(tradeData.toString(), AMERICAN_STYLE_EXERCISE_START_DATE_EMPTY_MSG));
    	}
    	
    	if(tradeData.getStyle()==OptionExerciseStyle.AMERICAN && getStringDateAsLocalDate(tradeData.getExcerciseStartDate()).isBefore(getStringDateAsLocalDate(tradeData.getTradeDate()))
    			&& getStringDateAsLocalDate(tradeData.getExcerciseStartDate()).isAfter(getStringDateAsLocalDate(tradeData.getExpiryDate()))){
    		validationResult.addError(new DataValidationError(tradeData.toString(), AMERICAN_STYLE_EXERCISE_START_DATE_NOT_VALID));
    	}
    	
    	if(getStringDateAsLocalDate(tradeData.getExpiryDate()).isAfter(getStringDateAsLocalDate(tradeData.getDeliveryDate()))
    			&& getStringDateAsLocalDate(tradeData.getPremiumDate()).isAfter(getStringDateAsLocalDate(tradeData.getDeliveryDate()))){
    		validationResult.addError(new DataValidationError(tradeData.toString(), OPTION_EXPIRY_PREMIUM_DATE_NOT_VALID));
    	}
    }
    
    private void validateForwardData(DataValidationViewObject validationResult, ForwardTradeDataObject tradeData){
    	if(tradeData.getValueDate()==null || isEmpty(tradeData.getValueDate().toString())){
    		validationResult.addError(new DataValidationError(tradeData.toString(), VALUE_DATE_EMPTY_MSG));
    	}
    	
    	if (getStringDateAsLocalDate(tradeData.getValueDate()).isBefore(getStringDateAsLocalDate(tradeData.getTradeDate()))) {
  		  validationResult.addError(new DataValidationError(tradeData.toString(), VALUE_DATE_IN_PAST_MSG));
        } 
  	  
  	  if(BusinessDateProvider.isBusinessDateFallsOnWeekendOrNonWorkingDay(getStringDateAsLocalDate(tradeData.getValueDate()))){
  		  validationResult.addError(new DataValidationError(tradeData.toString(), VALUE_DATE_WEEKEND_NON_WORKING_DAY_MSG));
  	  }
    }
       
}
