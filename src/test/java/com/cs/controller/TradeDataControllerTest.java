package com.cs.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cs.data.TradeDataWrapper;
import com.cs.validator.TradeDataValidator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeDataController.class)
@WebAppConfiguration
public class TradeDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private TradeDataValidator tradeDataValidator;

    private JacksonTester<List<TradeDataWrapper>> jsonTester;

    private List<TradeDataWrapper> tradeDataWrappers;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
        tradeDataWrappers = new ArrayList<>();
    }


    @Test
    public void testValidateTradeData() throws Exception {


        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("testTradeData.json").getFile());
        assertTrue(file.exists());

        InputStream is = new FileInputStream(file);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        final TradeDataWrapper[] tradeDatas = mapper.readValue(is, TradeDataWrapper[].class);

        final String tradeDataDTOJson = jsonTester.write(Arrays.asList(tradeDatas)).getJson();

        String expected = "{\"validationErrors\":[{\"tradeIdentifier\":\" [customer=PLUTO2, ccyPair=EURUSD, type=Forward, direction=BUY, tradeDate=2016-08-11, amount1=1000000.0, amount2=1120000.0, rate=1.12, legalEntity=CS Zurich, trader=Johann Baumfiddler, valueDate=2016-08-21]\",\"message\":\"Value date can't fall on weekend or non working day for currency\"},{\"tradeIdentifier\":\" [customer=PLUTO2, ccyPair=EURUSD, type=Forward, direction=BUY, tradeDate=2016-08-11, amount1=1000000.0, amount2=1120000.0, rate=1.12, legalEntity=CS Zurich, trader=Johann Baumfiddler, valueDate=2016-08-08]\",\"message\":\"Value date can't before than trade date\"},{\"tradeIdentifier\":\"[ForwardTradeDataObject= [customer=PLUT02, ccyPair=EURUSD, type=Forward, direction=BUY, tradeDate=2016-08-11, amount1=1000000.0, amount2=1120000.0, rate=1.12, legalEntity=CS Zurich, trader=Johann Baumfiddler , valueDate=2016-08-08]]\",\"message\":\"The given customer is not allowed to trade\"},{\"tradeIdentifier\":\" [customer=PLUT02, ccyPair=EURUSD, type=Forward, direction=BUY, tradeDate=2016-08-11, amount1=1000000.0, amount2=1120000.0, rate=1.12, legalEntity=CS Zurich, trader=Johann Baumfiddler , valueDate=2016-08-08]\",\"message\":\"Value date can't before than trade date\"},{\"tradeIdentifier\":\"[ForwardTradeDataObject= [customer=PLUTO3, ccyPair=EURUSD, type=Forward, direction=BUY, tradeDate=2016-08-11, amount1=1000000.0, amount2=1120000.0, rate=1.12, legalEntity=CS Zurich, trader=Johann Baumfiddler, valueDate=2016-08-22]]\",\"message\":\"The given customer is not allowed to trade\"},{\"tradeIdentifier\":\"[OptionTradeDataObject= [customer=PLUTO3, ccyPair=EURUSD, type=VanillaOption, direction=SELL, tradeDate=2016-08-11, amount1=1000000.0, amount2=1120000.0, rate=1.12, legalEntity=CS Zurich, trader=Johann Baumfiddler, style=AMERICAN, strategy=CALL, deliveryDate=2016-08-22, expiryDate=2016-08-19, excerciseStartDate=2016-08-10, payCcy=USD, premium=0.2, premiumCcy=USD, premiumType=%USD, premiumDate=2016-08-12]]\",\"message\":\"The given customer is not allowed to trade\"}]}";

        MvcResult result = this.mockMvc
                               .perform(post("/tradeData/validate").content(tradeDataDTOJson).contentType(APPLICATION_JSON_UTF8))
                               .andExpect(status().isOk())
                               .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                               .andReturn();

        JSONAssert.assertEquals(expected, result.getResponse()
                                                .getContentAsString(), false);



    }



}
