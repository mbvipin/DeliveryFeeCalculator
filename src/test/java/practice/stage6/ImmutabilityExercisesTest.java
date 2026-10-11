package practice.stage6;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ImmutabilityExercisesTest {

    private final ImmutabilityExercises ex = new ImmutabilityExercisesImpl();

    @Test
    void immutableCopyRejectsMutation() {
        List<Integer> source = List.of(1, 2, 3);
        List<Integer> copy = ex.makeImmutableCopy(source);

        assertEquals(List.of(1, 2, 3), copy);
        assertThrows(UnsupportedOperationException.class, () -> copy.add(4));
    }

    @Test
    void toUnmodifiableUpperRejectsMutation() {
        List<String> result = ex.toUnmodifiableUpper(List.of("a", "b"));

        assertEquals(List.of("A", "B"), result);
        assertThrows(UnsupportedOperationException.class, () -> result.add("C"));
    }

    @Test
    void concurrentWordCountCountsOccurrences() {
        Map<String, Integer> result = ex.concurrentWordCount(List.of("a", "b", "a", "c", "b", "a"));

        assertEquals(3, result.get("a"));
        assertEquals(2, result.get("b"));
        assertEquals(1, result.get("c"));
    }

    @Test
    void copyOnWriteDemoAppendsDuringIterationWithoutThrowing() {
        List<String> result = ex.copyOnWriteDemo(List.of("x", "y", "z"), "new");

        assertEquals(4, result.size());
        assertEquals("new", result.get(result.size() - 1));
    }
}
