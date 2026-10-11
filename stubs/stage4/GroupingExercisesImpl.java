package practice.stage4;

import practice.model.Employee;
import practice.model.Order;
import practice.model.Person;

import java.util.List;
import java.util.Map;

public class GroupingExercisesImpl implements GroupingExercises {

    @Override
    public Map<Integer, List<Person>> groupByAgeDecade(List<Person> people) {
        // TODO: Collectors.groupingBy
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Map<Character, Long> countWordsByFirstLetter(List<String> words) {
        // TODO: Collectors.groupingBy + Collectors.counting
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Map<String, Double> sumOrderTotalsByCustomer(List<Order> orders) {
        // TODO: Collectors.groupingBy + Collectors.summingDouble
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> nums) {
        // TODO: Collectors.partitioningBy
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Map<String, List<String>> namesByDepartment(List<Employee> employees) {
        // TODO: Collectors.groupingBy + Collectors.mapping
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<Integer> flattenAndDedupe(List<List<Integer>> lists) {
        // TODO: flatMap + distinct
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Map<String, Employee> highestPaidByDepartment(List<Employee> employees) {
        // TODO: Collectors.groupingBy + Collectors.collectingAndThen(Collectors.maxBy(...))
        throw new UnsupportedOperationException("not implemented");
    }
}
