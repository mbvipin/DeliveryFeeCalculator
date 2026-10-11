package practice.stage2;

import practice.model.Person;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComparatorExercisesImpl implements ComparatorExercises {

    @Override
    public List<Person> sortByAgeThenName(List<Person> people) {

        Comparator<Person>  comparatorByAge= Comparator.comparingInt(Person::age);
        Comparator<Person> comparatorByAgeThenName=comparatorByAge.thenComparing(Person::name);




        return people.stream().sorted(comparatorByAgeThenName).collect(Collectors.toUnmodifiableList());

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
