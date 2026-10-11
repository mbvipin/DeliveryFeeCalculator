package practice.stage5;

import practice.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Stage 5 — Optional. See README.md for problem descriptions.
 */
public interface OptionalExercises {

    /** First person strictly older than the given age, if any. */
    Optional<Person> firstOlderThan(List<Person> people, int age);

    /** person -> address -> city, or "Unknown" if any step is absent/null. */
    String cityOrUnknown(Optional<Person> person);

    /** a / b, empty if b is zero. */
    Optional<Integer> safeDivide(int a, int b);

    /** Sum of the list's elements, or 0 if the Optional is empty. */
    int sumOrZero(Optional<List<Integer>> maybeList);
}
