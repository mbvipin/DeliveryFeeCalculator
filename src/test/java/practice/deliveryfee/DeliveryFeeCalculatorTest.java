package practice.deliveryfee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;


public class DeliveryFeeCalculatorTest {

    DeliveryFeeCalculator feeCalculator;

    {
            feeCalculator = DeliveryFeeCalculator.withStandardPricing();

    }

    public DeliveryFeeCalculatorTest() {
    }

    @ParameterizedTest(name =" {0} kg -> ${1}" )
    @CsvSource(
            {
                    "10, 5.00",
                    "19.999,5.00",
                    "15,5.00",
                    "20, 12.50",
                    "49.999, 12.50",
                    "50, 25.00",
                    "99.999, 25.00",
                    "100, 40.00",
                    "100.01,40.30",
                    "100.2,40.30",
                    "101, 40.30",
                    "101.001,40.60",
                     "150,55.00",
                    "500, 160.00",
                    "20.000,12.50"
            }
    )
    void calculateFee(String weight,String expectedFees) throws RejectedException {
        assertEquals(new BigDecimal(expectedFees),feeCalculator.calculate(new BigDecimal(weight)));
    }

    @Test
    void rejectNullWeight()
    {
        assertThrows( RejectedException.class,()->feeCalculator.calculate(null));

    }

    @ParameterizedTest
    @ValueSource(strings ={"0","-1.00","19.9999",})
    void rejectInvalidWeight(String weight)
    {
        assertThrows( RejectedException.class,()->feeCalculator.calculate(new BigDecimal(weight)));

    }

    @ParameterizedTest
    @ValueSource(strings ={"0.001","9.999"})
    void rejectTooLightWeight(String weight)
    {
        assertThrows( RejectedException.class,()->feeCalculator.calculate(new BigDecimal(weight)));

    }

    @ParameterizedTest
    @ValueSource(strings ={"500.001","502"})
    void rejectExceeds(String weight)
    {
        assertThrows( RejectedException.class,()->feeCalculator.calculate(new BigDecimal(weight)));

    }

    @Test
    void rejectInvalidPrecision()
    {
        assertThrows( RejectedException.class,()->feeCalculator.calculate(new BigDecimal("19.9999")));
    }



}
