package practice.stage3;

import org.junit.jupiter.api.Test;
import practice.model.Person;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StreamExercisesTest {

    private final StreamExercises ex = new StreamExercisesImpl();

    @Test
    void computesStats() {
        IntSummaryStatistics stats = ex.computeStats(List.of(3, 1, 4, 1, 5));

        assertEquals(5, stats.getCount());
        assertEquals(14, stats.getSum());
        assertEquals(1, stats.getMin());
        assertEquals(5, stats.getMax());
        assertEquals(2.8, stats.getAverage(), 0.0001);
    }

    @Test
    void extractsInitialsOfLongNames() {
        List<String> names = List.of("Al", "Bob", "Charlie", "Dee");

        List<String> initials = ex.initialsOfLongNames(names, 3);

        assertEquals(List.of("B", "C", "D"), initials);
    }

    @Test
    void distinctSortedLengths() {
        List<String> strings = List.of("a", "bb", "cc", "ddd", "e");

        List<Integer> lengths = ex.distinctSortedLengths(strings);

        assertEquals(List.of(1, 2, 3), lengths);
    }

    @Test
    void allPositiveTrueCase() {
        assertTrue(ex.allPositive(List.of(1, 2, 3)));
    }

    @Test
    void allPositiveFalseCase() {
        assertFalse(ex.allPositive(List.of(1, -2, 3)));
    }

    @Test
    void anyNegativeTrueCase() {
        assertTrue(ex.anyNegative(List.of(1, -2, 3)));
    }

    @Test
    void anyNegativeFalseCase() {
        assertFalse(ex.anyNegative(List.of(1, 2, 3)));
    }

    @Test
    void joinsWithComma() {
        assertEquals("a, b, c", ex.joinWithComma(List.of("a", "b", "c")));
    }

    @Test
    void mapsNameToMaxAge() {
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 40),
                new Person("Alice", 25)
        );

        Map<String, Integer> result = ex.toNameAgeMapKeepMax(people);

        assertEquals(30, result.get("Alice"));
        assertEquals(40, result.get("Bob"));
        assertEquals(2, result.size());
    }
}
