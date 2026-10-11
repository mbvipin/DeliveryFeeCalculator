package practice.stage2;

import org.junit.jupiter.api.Test;
import practice.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComparatorExercisesTest {

    private final ComparatorExercises ex = new ComparatorExercisesImpl();

    @Test
    void sortsByAgeThenName() {


        List<Person> people = List.of(new Person("Vipin",40),
                new Person("Gayathri",38),
                new Person("Rhiti",9));


        List<Person> sorted = ex.sortByAgeThenName(people);

        assertEquals(List.of(
                new Person("Rhiti", 9),
                new Person("Gayathri", 38),
                new Person("Vipin", 40)
        ), sorted);
    }

    @Test
    void sortsByLengthDescThenAlpha() {
        List<String> strings = List.of("bb", "a", "ccc", "dd");

        List<String> sorted = ex.sortByLengthDescThenAlpha(strings);

        assertEquals(List.of("ccc", "bb", "dd", "a"), sorted);
    }

    @Test
    void findsKLargest() {
        List<Integer> nums = List.of(5, 1, 9, 3, 7, 2);

        List<Integer> result = new ArrayList<>(ex.kLargest(nums, 3));
        Collections.sort(result, Collections.reverseOrder());

        assertEquals(List.of(9, 7, 5), result);
    }

    @Test
    void mergesKSortedLists() {
        List<List<Integer>> lists = List.of(
                List.of(1, 4, 7),
                List.of(2, 5),
                List.of(0, 3, 6, 9)
        );

        List<Integer> merged = ex.mergeKSortedLists(lists);

        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 9), merged);
    }

    @Test
    void findsKClosestToOrigin() {
        List<int[]> points = List.of(
                new int[]{3, 3},   // dist^2 = 18
                new int[]{1, 1},   // dist^2 = 2
                new int[]{5, 5},   // dist^2 = 50
                new int[]{2, 0}    // dist^2 = 4
        );

        List<int[]> closest = ex.kClosestToOrigin(points, 2);
        List<Integer> distancesSquared = closest.stream()
                .map(p -> p[0] * p[0] + p[1] * p[1])
                .sorted()
                .toList();

        assertEquals(List.of(2, 4), distancesSquared);
    }

    @Test
    void findsTopNKeysByValue() {
        Map<String, Integer> map = Map.of("a", 10, "b", 50, "c", 30, "d", 40);

        List<String> top2 = ex.topNKeysByValue(map, 2);

        assertEquals(List.of("b", "d"), top2);
    }
}
