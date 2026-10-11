package practice.stage2;

import practice.model.Person;

import java.util.List;
import java.util.Map;

/**
 * Stage 2 — Comparator & ordering. See README.md for problem descriptions.
 */
public interface ComparatorExercises {

    /** Sort by age ascending, then name ascending for ties. */
    List<Person> sortByAgeThenName(List<Person> people);

    /** Sort by string length descending, then alphabetically ascending for ties. */
    List<String> sortByLengthDescThenAlpha(List<String> strings);

    /** Return the k largest numbers, using a heap (PriorityQueue), not a full sort. */
    List<Integer> kLargest(List<Integer> nums, int k);

    /** Merge several already-sorted (ascending) lists into one sorted list, using a PriorityQueue. */
    List<Integer> mergeKSortedLists(List<List<Integer>> lists);

    /** Return the k points closest to the origin (each point is {x, y}), using a max-heap of size k. */
    List<int[]> kClosestToOrigin(List<int[]> points, int k);

    /** Return the top n keys of the map, ordered by value descending. */
    List<String> topNKeysByValue(Map<String, Integer> map, int n);
}
