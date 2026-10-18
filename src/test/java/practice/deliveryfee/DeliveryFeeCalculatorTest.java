package practice.deliveryfee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static practice.deliveryfee.Num.bd;

/** Prices parcels against the standard table; boundaries are where the bugs hide. */
public class DeliveryFeeCalculatorTest {

    // LEARNING: JUnit 5 creates a new test class instance per test method, so this field
    // is rebuilt for every test and tests can't leak state into each other.
    private final DeliveryFeeCalculator feeCalculator = DeliveryFeeCalculator.withStandardPricing();

    /** Every rejected weight must be reported with the checked RejectedException. */
    private void assertRejected(BigDecimal weight) {
        assertThrows(RejectedException.class, () -> feeCalculator.calculate(weight));
    }

    // ---------------------------------------------------------------- fees

    // LEARNING: a CSV source puts many cases in one test, one row each. Rows sit just
    // below and exactly on each boundary, because off-by-one errors live there.
    @ParameterizedTest(name = "{0} kg -> {1}")
    @CsvSource({
            // 10 to under 20
            "10,       5.00",
            "15,       5.00",
            "19.999,   5.00",
            // 20 to under 50
            "20,       12.50",
            "20.000,   12.50",   // trailing zeros must not change the result
            "49.999,   12.50",
            // 50 to under 100
            "50,       25.00",
            "99.999,   25.00",
            // 100 and over: base 40.00 + 0.30 per started kg over 100
            "100,      40.00",
            "100.01,   40.30",   // any part of a kg counts as a full kg
            "100.2,    40.30",
            "101,      40.30",
            "101.001,  40.60",
            "150,      55.00",
            "500,      160.00"
    })
    void calculateFee(String weight, String expectedFees) throws RejectedException {
        // LEARNING: assertEquals on BigDecimal compares scale too, so this also proves
        // the result has exactly 2 decimals ("5.00", not "5").
        assertEquals(bd(expectedFees), feeCalculator.calculate(bd(weight)));
    }

    // ---------------------------------------------------------------- rejections

    @Test
    void rejectNullWeight() {
        assertRejected(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1.00"})
    void rejectNonPositiveWeight(String weight) {
        assertRejected(bd(weight));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.001", "9.999"})
    void rejectTooLightWeight(String weight) {
        assertRejected(bd(weight));
    }

    @ParameterizedTest
    @ValueSource(strings = {"500.001", "502"})
    void rejectExceeds(String weight) {
        assertRejected(bd(weight));
    }

    @Test
    void rejectInvalidPrecision() {
        assertRejected(bd("19.9999"));
    }
}
