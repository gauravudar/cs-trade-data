package com.cs.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.cs.data.TradeDataWrapper;
import com.cs.validator.TradeDataValidator;
import com.cs.viewobject.DataValidationViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tradeData")
public class TradeDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeDataController.class);
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private TradeDataValidator tradeDataValidator;

    @RequestMapping(value="/validate",method=RequestMethod.POST)
    public @ResponseBody DataValidationViewObject validateTradeData(@RequestBody List<TradeDataWrapper> trades) {
        final long start = System.currentTimeMillis();
        DataValidationViewObject viewObject = tradeDataValidator.validate(trades);
        LOGGER.info("Request to complete took {} ms", System.currentTimeMillis() - start);
        LOGGER.info("No of requests served {}", counter.incrementAndGet());
        return viewObject;
    }
    
   
}
