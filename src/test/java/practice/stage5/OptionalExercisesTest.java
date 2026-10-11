package practice.stage5;

import org.junit.jupiter.api.Test;
import practice.model.Address;
import practice.model.Person;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OptionalExercisesTest {

    private final OptionalExercises ex = new OptionalExercisesImpl();

    @Test
    void findsFirstOlderThan() {
        List<Person> people = List.of(
                new Person("Alice", 20),
                new Person("Bob", 35),
                new Person("Carol", 40)
        );

        Optional<Person> result = ex.firstOlderThan(people, 30);

        assertTrue(result.isPresent());
        assertEquals("Bob", result.get().name());
    }

    @Test
    void firstOlderThanEmptyWhenNoneMatch() {
        List<Person> people = List.of(new Person("Alice", 20));

        assertTrue(ex.firstOlderThan(people, 30).isEmpty());
    }

    @Test
    void cityOrUnknownWithFullChain() {
        Person person = new Person("Alice", 30, new Address("Boston"));

        assertEquals("Boston", ex.cityOrUnknown(Optional.of(person)));
    }

    @Test
    void cityOrUnknownWhenAddressMissing() {
        Person person = new Person("Alice", 30); // address is null

        assertEquals("Unknown", ex.cityOrUnknown(Optional.of(person)));
    }

    @Test
    void cityOrUnknownWhenPersonMissing() {
        assertEquals("Unknown", ex.cityOrUnknown(Optional.empty()));
    }

    @Test
    void safeDivideNormalCase() {
        assertEquals(5, ex.safeDivide(10, 2).get());
    }

    @Test
    void safeDivideByZeroIsEmpty() {
        assertTrue(ex.safeDivide(10, 0).isEmpty());
    }

    @Test
    void sumOrZeroWithValue() {
        assertEquals(6, ex.sumOrZero(Optional.of(List.of(1, 2, 3))));
    }

    @Test
    void sumOrZeroWhenEmpty() {
        assertEquals(0, ex.sumOrZero(Optional.empty()));
    }
}
