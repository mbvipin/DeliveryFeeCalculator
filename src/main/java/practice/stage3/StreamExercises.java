package practice.stage3;

import practice.model.Person;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

/**
 * Stage 3 — Streams fundamentals. See README.md for problem descriptions.
 */
public interface StreamExercises {

    /** Sum, average, max, min, count in one pass (IntStream.summaryStatistics()). */
    IntSummaryStatistics computeStats(List<Integer> nums);

    /** Names with length >= minLength, mapped to their uppercase first initial. */
    List<String> initialsOfLongNames(List<String> names, int minLength);

    /** Distinct string lengths, sorted ascending. */
    List<Integer> distinctSortedLengths(List<String> strings);

    boolean allPositive(List<Integer> nums);

    boolean anyNegative(List<Integer> nums);

    /** Join all strings with ", ". */
    String joinWithComma(List<String> strings);

    /** name -> age; if a name repeats, keep the larger age (Collectors.toMap with a merge function). */
    Map<String, Integer> toNameAgeMapKeepMax(List<Person> people);
}
