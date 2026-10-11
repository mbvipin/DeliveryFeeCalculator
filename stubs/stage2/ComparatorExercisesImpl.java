package practice.stage2;

import practice.model.Person;

import java.util.List;
import java.util.Map;

public class ComparatorExercisesImpl implements ComparatorExercises {

    @Override
    public List<Person> sortByAgeThenName(List<Person> people) {
        // TODO: Comparator.comparing(...).thenComparing(...)
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<String> sortByLengthDescThenAlpha(List<String> strings) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<Integer> kLargest(List<Integer> nums, int k) {
        // TODO: use a PriorityQueue (min-heap) of size k
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        // TODO: use a PriorityQueue to merge
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<int[]> kClosestToOrigin(List<int[]> points, int k) {
        // TODO: use a max-heap of size k
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<String> topNKeysByValue(Map<String, Integer> map, int n) {
        // TODO
        throw new UnsupportedOperationException("not implemented");
    }
}
