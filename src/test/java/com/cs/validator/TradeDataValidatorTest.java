package com.cs.validator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cs.data.TradeDataWrapper;
import com.cs.viewobject.DataValidationViewObject;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class TradeDataValidatorTest {
    @Autowired
    private TradeDataValidatorImpl tradeDataValidatorImpl;

    @TestConfiguration
    static class TradeDataValidatorImplTestContextConfiguration {

        @Bean
        public TradeDataValidator tradeDataValidator() {
            return new TradeDataValidatorImpl();
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
    }

    @Test
    public void testValidateTradeData() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("testTradeData.json").getFile());
        assertTrue(file.exists());

        InputStream is = new FileInputStream(file);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        final TradeDataWrapper[] tradeDatas = mapper.readValue(is,
                                                               TradeDataWrapper[].class);
        final List<TradeDataWrapper> tradeDataLst = new ArrayList<>(Arrays.asList(tradeDatas));
        final DataValidationViewObject data = tradeDataValidatorImpl.validate(tradeDataLst);
        assertNotNull(data);
        assertThat(data.getValidationErrors().size(), org.hamcrest.Matchers.is(6));
    }

    @Test
    public void testValidateTradeDataWithWrongPath() throws IOException {
        thrown.expect(NullPointerException.class);

        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("testTradeData11.json").getFile());
    }


}
